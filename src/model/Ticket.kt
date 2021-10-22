package model

import java.time.LocalDate


data class Ticket(

    var id: Long,

    var specId: Long,

    var date: LocalDate,

    var price: Int,

    var row: Int,

    var column: Int,
)
