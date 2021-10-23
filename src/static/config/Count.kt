package static.config

import model.HallSize
import static.glossary.Actors
import static.glossary.Roles
import static.glossary.Spectacles


object Count {

    val HALL = HallSize(2, 2)

    const val DATES = 50

    private val ACTORS_MAX = Actors.size

    private val ROLES_MAX = Roles.size

    private val SPECTACLES_MAX = Spectacles.size

    val ACTORS = 20.coerceIn(1..ACTORS_MAX)

    val ROLES = 20.coerceIn(1..ROLES_MAX)

    val SPECTACLES = 10.coerceIn(1..SPECTACLES_MAX)

    private val REPERTOIRES_MAX = DATES * SPECTACLES

    private val SPEC_ROLES_MAX = ACTORS * ROLES

    private val SPEC_ACTORS_MAX = ACTORS * SPECTACLES

    private val SPEC_ROLES_ACTORS_MAX = ACTORS * ROLES * SPECTACLES

    val SPEC_ROLES = 20.coerceIn(1..SPEC_ROLES_MAX)

    val SPEC_ACTORS = 20.coerceIn(1..SPEC_ACTORS_MAX)

    val SPEC_ROLES_ACTORS = 20.coerceIn(1..SPEC_ROLES_ACTORS_MAX)

    val REPERTOIRES = 20.coerceIn(1..REPERTOIRES_MAX)

    private val TICKETS_MAX = REPERTOIRES * HALL.columns * HALL.rows

    val TICKETS = 100.coerceIn(1..TICKETS_MAX)
}