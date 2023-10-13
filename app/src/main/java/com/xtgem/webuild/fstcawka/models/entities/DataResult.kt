package com.xtgem.webuild.fstcawka.models.entities

data class DataResult<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val emptyData: Boolean? = null
)