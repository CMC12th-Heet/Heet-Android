package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestResetPwd(
    val password: String,
    val email: String,
)
