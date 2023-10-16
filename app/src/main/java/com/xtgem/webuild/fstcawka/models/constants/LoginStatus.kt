package com.xtgem.webuild.fstcawka.models.constants

data class LoginStatus(
    val checking: Boolean? = null,
    val status: Boolean? = null,
    val reason: State? = null,
    val userId: String? = null
)


enum class State{
    REG,
    ID,
    Password
}