package com.xtgem.webuild.fstcawka.models.entities

data class DataResult<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val success: Boolean? = null,
    val sessionInvalid: Boolean? = null,
    val extra: Boolean? = null
)