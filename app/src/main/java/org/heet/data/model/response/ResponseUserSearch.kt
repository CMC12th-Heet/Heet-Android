package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserSearch(
    val email: String,
    val is_verify: Boolean,
    val password: String,
    val status: String?,
    val town: String,
    val user_id: Int,
    val username: String,
)
