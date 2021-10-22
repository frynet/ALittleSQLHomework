package model


data class SpecActor(

    var specId: Long,

    var actorId: Long,
) {
    override fun toString() = "($specId, $actorId)"
}
