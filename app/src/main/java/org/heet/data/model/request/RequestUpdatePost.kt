package org.heet.data.model.request

import com.google.gson.annotations.SerializedName

data class RequestUpdatePost(
    @SerializedName("content")
    val content: String,
    @SerializedName("mini_title")
    val miniTitle: String,
    @SerializedName("moving_tip")
    val movingTip: String,
    @SerializedName("ordering_tip")
    val orderingTip: String,
    @SerializedName("other_tips")
    val otherTips: String,
    @SerializedName("perfect_day")
    val perfectDay: String,
    @SerializedName("satisfaction")
    val satisfaction: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("together_with")
    val togetherWith: String
)
