package org.heet.domain.repository

interface StoreEmailPwdRepository {

    suspend fun getEmail(): String
    suspend fun getPwd(): String
    suspend fun getId(): String

    suspend fun updateEmail(email: String)
    suspend fun updatePwd(pwd: String)
    suspend fun updateId(id: String)
    suspend fun deleteEmail(email: String)
    suspend fun deletePwd(pwd: String)
    suspend fun deleteId(id: String)
}
