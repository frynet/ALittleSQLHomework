package model


data class Actor(

    var id: Long,

    var name: String,
) {
    override fun toString() = "($id, \'$name\')"

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Actor) return false

        return name == other.name || id == other.id
    }
}
