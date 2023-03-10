package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPostEmail(
    val email: String
)
