package model


data class SpecRole(

    var specId: Long,

    var roleId: Long,

    var main: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SpecRole) return false

        return specId == other.specId && roleId == other.roleId
    }
}
