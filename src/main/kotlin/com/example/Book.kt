package com.example

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Book @JsonCreator constructor(
    @param:JsonProperty("title") val title: String,
    @param:JsonProperty("author") val author: String,
)
