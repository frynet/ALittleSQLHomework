package model


data class Actor(

    var id: Long,

    var name: String,
) {
    override fun toString() = "($id, \'$name\')"
}
