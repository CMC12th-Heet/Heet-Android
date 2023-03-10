package org.heet.domain.repository

import kotlinx.coroutines.flow.Flow

interface AutoLoginRepository {

    var accessToken: String
    fun getDidLogin(): Flow<Boolean>
    fun getAccessToken(): Flow<String>
    suspend fun updateDidLogin()
    suspend fun deleteDidLogin()
    suspend fun updateAccessToken(token: String)
    suspend fun deleteAccessToken()
}
