package org.heet.domain.repository

interface UserInfoRepository {

    suspend fun getEmail(): String
    suspend fun getPwd(): String
    suspend fun getId(): String
    suspend fun getHometown(): String

    suspend fun updateEmail(email: String)
    suspend fun updatePwd(pwd: String)
    suspend fun updateId(id: String)
    suspend fun updateHometown(hometown: String)
    suspend fun deleteAll()
}
