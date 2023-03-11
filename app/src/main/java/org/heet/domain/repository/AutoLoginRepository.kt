package org.heet.domain.repository

import kotlinx.coroutines.flow.Flow

interface AutoLoginRepository {

    var accessToken: String
    fun getAccessToken(): Flow<String>
    suspend fun getToken(): String
    suspend fun updateAccessToken(token: String)
    suspend fun deleteAccessToken()
}
