package org.heet.data.repository

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.response.ResponsePostEmail
import org.heet.data.service.SignUpService
import org.heet.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val signUpService: SignUpService) :
    SignUpRepository {
    override suspend fun postEmail(requestPostEmail: RequestPostEmail): ResponsePostEmail {
        return signUpService.postEmail(requestPostEmail)
    }
}
