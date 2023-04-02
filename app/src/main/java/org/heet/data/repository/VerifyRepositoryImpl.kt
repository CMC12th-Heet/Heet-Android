package org.heet.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import org.heet.domain.repository.VerifyRepository
import javax.inject.Inject

class VerifyRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : VerifyRepository {
    override suspend fun getIsVerify(): Boolean = dataStore.data.first()[IS_VERIFY] ?: false

    override suspend fun updateIsVerify(verify: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_VERIFY] = verify
        }
    }

    override suspend fun deleteIsVerify() {
        dataStore.edit {
            it[IS_VERIFY] = false
        }
    }

    companion object {
        val IS_VERIFY = booleanPreferencesKey("is_verfiy")
    }
}
