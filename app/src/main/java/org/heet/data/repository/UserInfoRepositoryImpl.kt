package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import org.heet.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserInfoRepository {

    override suspend fun getEmail(): String = dataStore.data.first()[EMAIL] ?: ""

    override suspend fun getPwd(): String = dataStore.data.first()[PWD] ?: ""
    override suspend fun getId(): String = dataStore.data.first()[ID] ?: ""
    override suspend fun getHometown(): String = dataStore.data.first()[HOMETOWN] ?: ""

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

    override suspend fun updateHometown(hometown: String) {
        dataStore.edit { preferences ->
            preferences[HOMETOWN] = hometown
        }
    }

    override suspend fun deleteAll() {
        dataStore.edit {
            it[EMAIL] = ""
            it[PWD] = ""
            it[ID] = ""
            it[HOMETOWN] = ""
        }
    }

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val PWD = stringPreferencesKey("pwd")
        val ID = stringPreferencesKey("id")
        val HOMETOWN = stringPreferencesKey("hometown")
    }
}
