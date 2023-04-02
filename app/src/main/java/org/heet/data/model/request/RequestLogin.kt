package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLogin(
    val email: String,
    val password: String,
)
