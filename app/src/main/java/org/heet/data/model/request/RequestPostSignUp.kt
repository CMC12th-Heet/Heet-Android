package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPostSignUp(
    val email: String,
    val username: String,
    val password: String,
    val town: String
)
