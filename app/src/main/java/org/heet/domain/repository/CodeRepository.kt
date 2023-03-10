package org.heet.domain.repository

import kotlinx.coroutines.flow.Flow

interface CodeRepository {
    fun getCode(): Flow<Long>
    suspend fun updateCode(code: Long)
    suspend fun deleteCode()
}
