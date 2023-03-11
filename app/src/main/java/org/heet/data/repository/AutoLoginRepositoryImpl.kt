package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

class AutoLoginRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AutoLoginRepository {

    override var accessToken = ""

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getAccessToken().collect {
                accessToken = it
            }
        }
    }

    override fun getAccessToken(): Flow<String> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }

    override suspend fun getToken(): String = dataStore.data.first()[ACCESS_TOKEN] ?: ""

    override suspend fun updateAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    override suspend fun deleteAccessToken() {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = ""
        }
    }

    companion object {
        val DID_LOGIN = booleanPreferencesKey("did_login")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }
}
