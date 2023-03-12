package org.heet.domain.repository

import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.*

interface StoreRepository {

    suspend fun postStore(requestPostStore: RequestPostStore): ResponsePostStore
    suspend fun getStore(keyword: String): List<ResponseGetStore>
}
