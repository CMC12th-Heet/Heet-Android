package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePost(
    val title: String,
    val mini_title: String,
    val content: String,
    val file_url: String,
    val satisfaction: Int?,
    val together_with: String?,
    val is_local: Boolean,
    val user: User,
    val store: Store,
    val perfect_day: String?,
    val moving_tip: String?,
    val ordering_tip: String?,
    val other_tips: String?,
    val post_id: Int?,
    val created_at: String?
) {
    @Serializable
    data class Store(
        val store_id: Int,
        val name: String,
        val url: String,
        val address: String
    )

    @Serializable
    data class User(
        val user_id: Int,
        val email: String,
        val username: String,
        val password: String,
        val is_verify: Boolean,
        val town: String
    )
}
