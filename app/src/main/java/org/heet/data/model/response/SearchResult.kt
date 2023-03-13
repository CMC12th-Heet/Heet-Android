package org.heet.data.model.response

data class SearchResult(
    val storeName: String,
    val address: String,
    val url: String,
    val isSelected: Boolean
)
