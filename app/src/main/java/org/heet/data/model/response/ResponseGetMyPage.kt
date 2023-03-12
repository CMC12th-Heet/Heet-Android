package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMyPage(
    val user_id: Int,
    val email: String,
    val username: String,
    val password: String,
    val is_verify: Boolean,
    val town: String
)
