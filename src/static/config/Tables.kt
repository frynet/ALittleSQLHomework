package static.config

object Tables {

    enum class TableName(private val _name: String) {

        ACTORS("actors"),

        ROLES("roless"),

        SPECTACLES("spectacles"),

        SPEC_ROLES("spectacles_roles"),

        SPEC_ACTORS("spectacles_actors"),

        SPEC_ROLES_ACTORS("spectacles_roles_actors"),

        REPERTOIRES("repertoires"),

        HALL("hall"),

        TICKETS("sale_tickets");

        override fun toString(): String {
            return _name
        }
    }

    val items = mapOf(

        TableName.ACTORS to listOf("id", "name"),

        TableName.ROLES to listOf("id", "title"),

        TableName.SPECTACLES to listOf("id", "title"),

        TableName.SPEC_ROLES to listOf("id_spec", "id_role", "main"),

        TableName.SPEC_ACTORS to listOf("id_spec", "id_actor"),

        TableName.SPEC_ROLES_ACTORS to listOf("id_spec", "id_role", "id_actor"),

        TableName.REPERTOIRES to listOf("id_spec", "_date", "price"),

        TableName.HALL to listOf("_row", "_column"),

        TableName.TICKETS to listOf("id_spec", "_date", "price", "_row", "_col")
    )
}