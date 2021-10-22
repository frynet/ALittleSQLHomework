package static.config

import model.HallSize
import static.glossary.Actors
import static.glossary.Roles


object Count {

    const val SPECTACLES = 10

    const val TICKETS = 20

    const val DATES = 50

    private val ACTORS = Actors.size

    private val ROLES = Roles.size

    private val REPERTOIRES_MAX = DATES * SPECTACLES

    private val SPEC_ROLES_MAX = ACTORS * ROLES

    private val SPEC_ACTORS_MAX = ACTORS * SPECTACLES

    private val SPEC_ROLES_ACTORS_MAX = ACTORS * ROLES * SPECTACLES

    val SPEC_ROLES = 20.coerceIn(1..SPEC_ROLES_MAX)

    val SPEC_ACTORS = 20.coerceIn(1..SPEC_ACTORS_MAX)

    val SPEC_ROLES_ACTORS = 20.coerceIn(1..SPEC_ROLES_ACTORS_MAX)

    val REPERTOIRES = 20.coerceIn(1..REPERTOIRES_MAX)

    val HALL = HallSize(10, 10)
}