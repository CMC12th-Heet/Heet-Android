package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetPost(
    val posts: List<Post>
) {
    @Serializable
    data class Post(
        val post_id: Int,
        val title: String,
        val mini_title: String,
        val content: String,
        val file_url: String,
        val satisfaction: Int?,
        val together_with: String?,
        val perfect_day: String?,
        val moving_tip: String?,
        val ordering_tip: String?,
        val other_tips: String?,
        val is_local: Boolean,
        val created_at: String,
        val user: User,
        val store: Store,
        val urlList: List<String>
    ) {
        @Serializable
        data class Store(
            val address: String,
            val name: String,
            val store_id: Int,
            val url: String
        )

        @Serializable
        data class User(
            val email: String,
            val is_verify: Boolean,
            val password: String,
            val town: String,
            val user_id: Int,
            val username: String,
            val status: String?
        )
    }
}
