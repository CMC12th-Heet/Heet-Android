package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMyPage(
    val user_id: Int,
    val email: String,
    val username: String,
    val password: String,
    val is_verify: Boolean,
    val town: String,
    val status: String?,
    val post: List<Post>,
    val post_count: Int,
    val follower_count: Int,
    val following_count: Int,
) {
    @Serializable
    data class Post(
        val content: String,
        val created_at: String,
        val file_url: String,
        val is_local: Boolean,
        val mini_title: String,
        val moving_tip: String?,
        val ordering_tip: String?,
        val other_tips: String?,
        val perfect_day: String?,
        val post_id: Int,
        val satisfaction: Int?,
        val store: Store,
        val title: String,
        val together_with: String?,
        val user: User,
    ) {
        @Serializable
        data class Store(
            val address: String,
            val name: String,
            val store_id: Int,
            val url: String,
        )

        @Serializable
        data class User(
            val email: String,
            val is_verify: Boolean,
            val password: String,
            val status: String?,
            val town: String,
            val user_id: Int,
            val username: String,
        )
    }
}
