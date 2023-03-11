package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseIncheonCity(
    val incheon: List<String>
)
