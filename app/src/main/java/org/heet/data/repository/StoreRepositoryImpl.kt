package org.heet.data.repository

import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.*
import org.heet.data.service.StoreService
import org.heet.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeService: StoreService
) : StoreRepository {

    override suspend fun postStore(requestPostStore: RequestPostStore): Int {
        return storeService.postStore(requestPostStore)
    }

    override suspend fun getStore(keyword: String): List<ResponseGetStore> {
        return storeService.getStore(keyword)
    }
}
