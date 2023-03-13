package org.heet.domain.repository

interface VerifyRepository {
    suspend fun getIsVerify(): Boolean
    suspend fun updateIsVerify(verify: Boolean)
    suspend fun deleteIsVerify()
}
