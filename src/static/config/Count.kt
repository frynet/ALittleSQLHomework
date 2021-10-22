package static.config

import static.glossary.*

object Count {

    const val SPECTACLES = 10

    const val REPERTOIRES = 20

    const val HALL = 20

    const val TICKETS = 20

    private val ACTORS = Actors.size

    private val ROLES = Roles.size

    private val SPEC_ROLES_MAX = ACTORS * ROLES

    private val SPEC_ACTORS_MAX = ACTORS * SPECTACLES

    private val SPEC_ROLES_ACTORS_MAX = ACTORS * ROLES * SPECTACLES

    val SPEC_ROLES = 20.coerceIn(1..SPEC_ROLES_MAX)

    val SPEC_ACTORS = 20.coerceIn(1..SPEC_ACTORS_MAX)

    val SPEC_ROLES_ACTORS = 20.coerceIn(1..SPEC_ROLES_ACTORS_MAX)
}