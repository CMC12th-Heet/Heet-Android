package org.heet.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPostStore(
    val name: String,
    val url: String,
    val address: String,
)
