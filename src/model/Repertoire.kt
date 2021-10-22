package model

import java.time.LocalDate


data class Repertoire(

    var specId: Long,

    var date: LocalDate,

    var price: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Repertoire) return false

        return specId == other.specId && date == other.date
    }
}