package model


data class Role(

    var id: Long,

    var title: String,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Role) return false

        return id == other.id || title == other.title
    }

    override fun toString() = "(\'$title\')"
}
