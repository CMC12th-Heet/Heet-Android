package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.heet.data.repository.AutoLoginRepositoryImpl.PreferencesKeys.ACCESS_TOKEN
import org.heet.data.repository.AutoLoginRepositoryImpl.PreferencesKeys.DID_LOGIN
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

class AutoLoginRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AutoLoginRepository {

    override var accessToken = ""

    private object PreferencesKeys {
        val DID_LOGIN = booleanPreferencesKey("did_login")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getAccessToken().collect {
                accessToken = it
            }
        }
    }

    override fun getDidLogin(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DID_LOGIN] ?: false
    }

    override fun getAccessToken(): Flow<String> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }

    override suspend fun updateDidLogin() {
        dataStore.edit { preferences ->
            preferences[DID_LOGIN] = true
        }
    }

    override suspend fun deleteDidLogin() {
        dataStore.edit { preferences ->
            preferences[DID_LOGIN] = false
        }
    }

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
}
