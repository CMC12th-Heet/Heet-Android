package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSeoulCity(
    val seoul: List<String>,
)
