package org.heet.domain.repository

import org.heet.data.model.response.ResponseUserSearch

interface SearchRepository {

    suspend fun userSearch(keyword: String): List<ResponseUserSearch>
}
