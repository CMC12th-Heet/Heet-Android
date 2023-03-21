package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestUpdatePost(
    val title: String,
    val mini_title: String,
    val content: String,
    val satisfaction: Int?,
    val together_with: String?,
    val perfect_day: String?,
    val moving_tip: String?,
    val ordering_tip: String?,
    val other_tips: String?
)
