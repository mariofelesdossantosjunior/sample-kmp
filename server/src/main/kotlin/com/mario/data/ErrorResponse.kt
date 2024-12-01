package com.mario.data

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String
)