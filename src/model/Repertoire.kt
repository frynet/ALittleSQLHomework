package model

import java.time.LocalDate


data class Repertoire(

    var specId: Long,

    var date: LocalDate,

    var price: Int,
)