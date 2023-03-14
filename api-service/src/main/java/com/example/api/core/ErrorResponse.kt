package com.example.api.core

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: String = "",
    val error: String = "",
    val message: String = ""
) {
    companion object {
        fun getEmptyErrorResponse(): ErrorResponse {
            return ErrorResponse("", "", "")
        }
    }
}