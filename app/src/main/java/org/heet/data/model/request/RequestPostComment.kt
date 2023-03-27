package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPostComment(
    val content: String,
)
