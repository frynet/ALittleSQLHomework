package model


data class SpecRoleActor(

    var specId: Long,

    var roleId: Long,

    var actorId: Long,
) {
    override fun toString() = "($specId, $roleId, $actorId)"
}
