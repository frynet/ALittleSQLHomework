package model


data class HallPlace(

    var row: Int,

    var column: Int,
) {
    override fun toString() = "($row, $column)"
}
