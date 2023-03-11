package org.heet.domain.repository

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.request.RequestPostSignUp
import org.heet.data.model.response.*

interface SignUpRepository {
    suspend fun postEmail(requestPostEmail: RequestPostEmail): ResponsePostEmail

    suspend fun findDuplicate(username: String): ResponseFindDuplicate

    suspend fun signUp(requestPostSignUp: RequestPostSignUp): ResponseSignUp

    suspend fun getSeoulCity(name: String): ResponseSeoulCity
    suspend fun getGyeonggin(name: String): ResponseGyeonggiCity
    suspend fun getIncheon(name: String): ResponseIncheonCity
}
