package org.heet.domain.repository

interface StoreEmailPwdRepository {

    suspend fun getEmail(): String
    suspend fun getPwd(): String
    suspend fun getId(): String
    suspend fun getHometown(): String

    suspend fun updateEmail(email: String)
    suspend fun updatePwd(pwd: String)
    suspend fun updateId(id: String)
    suspend fun updateHometown(hometown: String)
    suspend fun deleteEmail()
    suspend fun deletePwd()
    suspend fun deleteId()
    suspend fun deleteHometown()
}
