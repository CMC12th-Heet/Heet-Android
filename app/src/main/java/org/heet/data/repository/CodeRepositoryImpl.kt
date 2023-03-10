package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.heet.data.repository.CodeRepositoryImpl.PreferencesKeys.CODE
import org.heet.domain.repository.CodeRepository
import javax.inject.Inject

class CodeRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    CodeRepository {

    override var code = 0L

    private object PreferencesKeys {
        val CODE = longPreferencesKey("code")
    }

    override fun getCode(): Flow<Long> = dataStore.data.map { preferences ->
        preferences[CODE] ?: 0
    }

    override suspend fun updateCode(code: Long) {
        dataStore.edit { preferences ->
            preferences[CODE] = code
        }
    }

    override suspend fun deleteCode() {
        dataStore.edit { preferences ->
            preferences[CODE] = 0
        }
    }
}
