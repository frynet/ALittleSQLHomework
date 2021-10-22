package model


data class Spectacle(

    var id: Long,

    var title: String,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Spectacle) return false

        return id == other.id || title == other.title
    }

    override fun toString() = "($id, \'$title\')"
}
