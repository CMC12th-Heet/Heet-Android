package org.heet.domain.repository

interface AddressRepository {
    suspend fun getSelectStore(): String
    suspend fun getSelectStoreNum(): Int

    suspend fun updateSelectStore(storeName: String)
    suspend fun deleteSelectStore()

    suspend fun updateSelectStoreNum(storeNum: Int)
    suspend fun deleteSelectStoreNum()
}
