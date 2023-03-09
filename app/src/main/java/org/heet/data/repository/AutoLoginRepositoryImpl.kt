package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.heet.data.repository.AutoLoginRepositoryImpl.PreferencesKeys.DID_LOGIN
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

class AutoLoginRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AutoLoginRepository {

    private object PreferencesKeys {
        val DID_LOGIN = booleanPreferencesKey("did_login")
    }

    override fun getPreferencesDidLogin(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DID_LOGIN] ?: false
    }

    override suspend fun updatePreferencesDidLogin() {
        dataStore.edit { preferences ->
            preferences[DID_LOGIN] = true
        }
    }

    override suspend fun deletePreferencesDidLogin() {
        dataStore.edit { preferences ->
            preferences[DID_LOGIN] = false
        }
    }
}
