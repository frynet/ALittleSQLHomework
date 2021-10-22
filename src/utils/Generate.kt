package utils

import model.*
import static.config.Count
import static.config.Tables
import static.config.Tables.TableName
import static.glossary.*


class Generate {

    companion object {

        private const val tab = "\t"
        private const val endl = "\n"
        private const val comma = ","
        private const val semicolon = ";"

        private val hall = mutableListOf<Hall>()
        private val roles = mutableListOf<Role>()
        private val actors = mutableListOf<Actor>()
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