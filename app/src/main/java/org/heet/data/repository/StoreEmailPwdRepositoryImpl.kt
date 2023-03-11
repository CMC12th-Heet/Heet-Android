package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

class StoreEmailPwdRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    StoreEmailPwdRepository {

    override suspend fun getEmail(): String = dataStore.data.first()[EMAIL] ?: ""

    override suspend fun getPwd(): String = dataStore.data.first()[PWD] ?: ""
    override suspend fun getId(): String = dataStore.data.first()[ID] ?: ""

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

    override suspend fun updateId(id: String) {
        dataStore.edit { preferences ->
            preferences[ID] = id
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

    override suspend fun deleteId(id: String) {
        dataStore.edit {
            it[PWD] = ""
        }
    }

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val PWD = stringPreferencesKey("pwd")
        val ID = stringPreferencesKey("id")
    }
}
