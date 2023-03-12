package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestModifyMyPage(
    val username: String,
    val password: String
)
