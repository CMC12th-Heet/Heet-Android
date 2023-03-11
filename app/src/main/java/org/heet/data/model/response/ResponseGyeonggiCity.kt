package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGyeonggiCity(
    val gyeonggi: List<String>
)
