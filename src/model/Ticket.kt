package model

import java.time.LocalDate


data class Ticket(

    var id: Long,

    var specId: Long,

    var date: LocalDate,

    var price: Int,

    var row: Int,

    var column: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Ticket) return false

        return specId == other.specId && date == other.date && row == other.row && column == other.column
    }
}
