package org.heet.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFindDuplicate(
    val isDuplicated: Boolean
)
