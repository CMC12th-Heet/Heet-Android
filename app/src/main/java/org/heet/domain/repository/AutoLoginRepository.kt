package org.heet.domain.repository

import kotlinx.coroutines.flow.Flow

interface AutoLoginRepository {
    fun getPreferencesDidLogin(): Flow<Boolean>
    suspend fun updatePreferencesDidLogin()
    suspend fun deletePreferencesDidLogin()
}
