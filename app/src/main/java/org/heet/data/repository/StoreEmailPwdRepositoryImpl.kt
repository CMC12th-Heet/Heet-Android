package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

class StoreEmailPwdRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    StoreEmailPwdRepository {

    override fun getEmail(): Flow<String> = dataStore.data.map { preferences ->
        preferences[EMAIL] ?: ""
    }

    override fun getPwd(): Flow<String> = dataStore.data.map { preferences ->
        preferences[PWD] ?: ""
    }

    override suspend fun updateEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    override suspend fun updatePwd(pwd: String) {
        dataStore.edit { preferences ->
            preferences[PWD] = pwd
        }
    }

    override suspend fun deleteEmail(email: String) {
        dataStore.edit {
            it[EMAIL] = ""
        }
    }

    override suspend fun deletePwd(pwd: String) {
        dataStore.edit {
            it[PWD] = ""
        }
    }

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val PWD = stringPreferencesKey("pwd")
    }
}
