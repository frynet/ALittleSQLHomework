package utils

import model.*
import static.config.Count
import static.config.Tables
import static.config.Tables.TableName
import static.glossary.Actors
import static.glossary.Roles
import static.glossary.Spectacles
import java.time.LocalDate
import kotlin.random.Random


class Generate {

    companion object {

        private const val tab = "\t"
        private const val endl = "\n"
        private const val comma = ","
        private const val semicolon = ";"

        private val roles = mutableListOf<Role>()
        private val actors = mutableListOf<Actor>()
        private val hall = mutableListOf<HallPlace>()
        private val tickets = mutableListOf<Ticket>()
        private val specRoles = mutableListOf<SpecRole>()
        private val specActors = mutableListOf<SpecActor>()
        private val spectacles = mutableListOf<Spectacle>()
        private val repertoires = mutableListOf<Repertoire>()
        private val specRoleActors = mutableListOf<SpecRoleActor>()


        private fun insertFormat(tablename: String, columns: List<String>): String {
            val cols = columns
                .toString()
                .replace('[', '(')
                .replace(']', ')')

            return "INSERT INTO $tablename $cols VALUES$endl"
        }

        private fun insertRecords(table: TableName, records: Iterable<Any>): String {
            var result = ""

            result += insertFormat(table.toString(), Tables.items[table]!!)

            records.forEach {
                result += tab + it + comma + endl
            }

            return result.replaceAfterLast(")", semicolon) + endl + endl
        }

        private fun actors(): String {
            var id = 0L

            Actors.forEach {
                actors.add(Actor(id++, it))
            }

            return insertRecords(TableName.ACTORS, actors)
        }

        private fun roles(): String {
            var id = 0L

            Roles.forEach {
                roles.add(Role(id++, it))
            }

            return insertRecords(TableName.ROLES, roles)
        }

        private fun spectacles(): String {
            var id = 0L

            repeat(Count.SPECTACLES) {
                spectacles.add(Spectacle(id++, Spectacles.random()))
            }

            return insertRecords(TableName.SPECTACLES, spectacles)
        }

        private fun specRoles(): String {
            var record: SpecRole

            repeat(Count.SPEC_ROLES) {
                record = SpecRole(
                    spectacles.random().id,
                    roles.random().id,
                    Random.nextBoolean()
                )

                if (!specRoles.contains(record)) {
                    specRoles.add(record)
                }
            }

            return insertRecords(TableName.SPEC_ROLES, specRoles)
        }

        private fun specActors(): String {
            var record: SpecActor

            repeat(Count.SPEC_ACTORS) {
                record = SpecActor(
                    spectacles.random().id,
                    actors.random().id
                )

                if (!specActors.contains(record)) {
                    specActors.add(record)
                }
            }

            return insertRecords(TableName.SPEC_ACTORS, specActors)
        }

        private fun findWhenSpecIdSatisfyToC6AndC9Constraints(): Set<Long> {
            val result = specActors
                .groupBy { it.specId }
                .keys
                .intersect(specRoles.groupBy { it.specId }.keys)

            if (result.isEmpty()) {
                throw Exception(
                    "spectacles roles and actors will be empty: not found spectacle ID which satisfy C6 and C9 constraints"
                )
            }

            return result
        }

        private fun specRolesActors(): String {
            var specId: Long
            var record: SpecRoleActor
            val specIds = findWhenSpecIdSatisfyToC6AndC9Constraints()
            val r = specRoles.groupBy { it.specId }
            val a = specActors.groupBy { it.specId }

            repeat(Count.SPEC_ROLES_ACTORS) {
                specId = specIds.random()

                record = SpecRoleActor(
                    specId,
                    r[specId]!!.random().roleId,
                    a[specId]!!.random().actorId
                )

                if (!specRoleActors.contains(record)) {
                    specRoleActors.add(record)
                }
            }

            return insertRecords(TableName.SPEC_ROLES_ACTORS, specRoleActors)
        }

        private fun datesAfter(date: LocalDate, count: Int = 1): List<LocalDate> {
            val result = mutableListOf<LocalDate>()

            repeat(count) {
                result.add(date.plusDays(Random.nextLong(4, 356)))
            }

            return result
        }

        private fun datesBefore(date: LocalDate, count: Int = 1): List<LocalDate> {
            val result = mutableListOf<LocalDate>()

            repeat(count) {
                result.add(date.minusDays(Random.nextLong(4, 356)))
            }

            return result
        }

        private fun repertoires(): String {
            var record: Repertoire
            val after = datesAfter(LocalDate.now(), Count.DATES / 2)
            val before = datesBefore(LocalDate.now(), Count.DATES / 2)

            repeat(Count.REPERTOIRES) {
                record = Repertoire(
                    spectacles.random().id,
                    (after + before).random(),
                    Random.nextInt(100, 300)
                )

                if (!repertoires.contains(record)) {
                    repertoires.add(record)
                }
            }

            return insertRecords(TableName.REPERTOIRES, repertoires)
        }

        private fun hall(): String {
            var place: HallPlace

            for (i in 1..Count.HALL.rows) {
                for (j in 1..Count.HALL.columns) {
                    place = HallPlace(i, j)

                    if (!hall.contains(place)) {
                        hall.add(place)
                    }
                }
            }

            return insertRecords(TableName.HALL, hall)
        }

        private fun containedPlace(size: HallSize) = HallPlace(
            row = Random.nextInt(1, size.rows),
            column = Random.nextInt(1, size.columns)
        )

        private fun tickets(): String {
            var id = 0L
            var ticket: Ticket
            var rep: Repertoire
            var place: HallPlace

            repeat(Count.TICKETS) {
                rep = repertoires.random()
                place = containedPlace(Count.HALL)
                ticket = Ticket(
                    id++,
                    rep.specId,
                    rep.date,
                    rep.price,
                    place.row,
                    place.column
                )

                if (!tickets.contains(ticket)) {
                    tickets.add(ticket)
                }
            }

            return insertRecords(TableName.TICKETS, tickets)
        }

        private fun table(name: TableName): String {
            return when (name) {

                TableName.ACTORS -> actors()

                TableName.ROLES -> roles()

                TableName.SPECTACLES -> spectacles()

                TableName.SPEC_ROLES -> specRoles()

                TableName.SPEC_ACTORS -> specActors()

                TableName.SPEC_ROLES_ACTORS -> specRolesActors()

                TableName.REPERTOIRES -> repertoires()

                TableName.HALL -> hall()

                TableName.TICKETS -> tickets()
            }
        }

        fun fileAsString(): String {
            var result = ""

            Tables.items.keys.forEach {
                result += table(it)
            }

            return result
        }
    }
}