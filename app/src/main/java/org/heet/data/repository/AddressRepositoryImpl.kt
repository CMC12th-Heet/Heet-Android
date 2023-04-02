package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import org.heet.domain.repository.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AddressRepository {

    override suspend fun getSelectStore(): String = dataStore.data.first()[STORE_NAME] ?: ""

    override suspend fun getSelectStoreNum(): Int = dataStore.data.first()[STORE_NUM] ?: 0

    override suspend fun updateSelectStore(storeName: String) {
        dataStore.edit { preferences ->
            preferences[STORE_NAME] = storeName
        }
    }

    override suspend fun deleteSelectStore() {
        dataStore.edit { preferences ->
            preferences[STORE_NAME] = ""
        }
    }

    override suspend fun updateSelectStoreNum(storeNum: Int) {
        dataStore.edit { preferences ->
            preferences[STORE_NUM] = storeNum
        }
    }

    override suspend fun deleteSelectStoreNum() {
        dataStore.edit { preferences ->
            preferences[STORE_NUM] = -1
        }
    }

    companion object {
        val STORE_NAME = stringPreferencesKey("store_name")
        val STORE_NUM = intPreferencesKey("store_num")
    }
}
