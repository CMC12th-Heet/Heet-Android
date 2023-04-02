package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseResetPostEmail(
    val code: String,
)
