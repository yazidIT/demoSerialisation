package com.example

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Nullable
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ResponseModel<out T> @JsonCreator constructor(
    @param:JsonProperty("code") val code: Int,

    @param:JsonProperty("data")
    @field:Nullable
    val data: T? = null
)
