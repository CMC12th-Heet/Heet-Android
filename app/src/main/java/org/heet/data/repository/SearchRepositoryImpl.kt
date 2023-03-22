package org.heet.data.repository

import org.heet.data.model.response.ResponseUserSearch
import org.heet.data.service.SearchService
import org.heet.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {

    override suspend fun userSearch(keyword: String): List<ResponseUserSearch> {
        return searchService.searchUser(keyword)
    }
}
