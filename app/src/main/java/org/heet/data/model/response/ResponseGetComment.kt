package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetComment(
    val comment_id: Int,
    val content: String,
    val created_at: String?,
    val user: User
) {
    @Serializable
    data class User(
        val user_id: Int,
        val email: String,
        val username: String,
        val password: String,
        val is_verify: Boolean,
        val town: String,
        val status: String?
    )
}
