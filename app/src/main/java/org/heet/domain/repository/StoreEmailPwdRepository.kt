package org.heet.domain.repository

import kotlinx.coroutines.flow.Flow

interface StoreEmailPwdRepository {

    fun getEmail(): Flow<String>
    fun getPwd(): Flow<String>

    suspend fun updateEmail(email: String)
    suspend fun updatePwd(pwd: String)
    suspend fun deleteEmail(email: String)
    suspend fun deletePwd(pwd: String)
}
