package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostComment(
    val message: String,
)
