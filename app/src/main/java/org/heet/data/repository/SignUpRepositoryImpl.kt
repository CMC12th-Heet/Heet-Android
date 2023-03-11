package org.heet.data.repository

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.request.RequestPostSignUp
import org.heet.data.model.response.*
import org.heet.data.service.SignUpService
import org.heet.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val signUpService: SignUpService) :
    SignUpRepository {
    override suspend fun postEmail(requestPostEmail: RequestPostEmail): ResponsePostEmail {
        return signUpService.postEmail(requestPostEmail)
    }

    override suspend fun findDuplicate(username: String): ResponseFindDuplicate {
        return signUpService.postFindDuplicate(username)
    }

    override suspend fun signUp(requestPostSignUp: RequestPostSignUp): ResponseSignUp {
        return signUpService.postSingUp(requestPostSignUp)
    }

    override suspend fun getSeoulCity(name: String): ResponseSeoulCity {
        return signUpService.getSeoulCity(name)
    }

    override suspend fun getGyeonggin(name: String): ResponseGyeonggiCity {
        return signUpService.getGyeonggin(name)
    }

    override suspend fun getIncheon(name: String): ResponseIncheonCity {
        return signUpService.getIncheon(name)
    }
}
