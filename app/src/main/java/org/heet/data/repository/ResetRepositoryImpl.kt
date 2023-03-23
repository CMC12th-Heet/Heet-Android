package org.heet.data.repository

import org.heet.data.model.request.RequestResetPwd
import org.heet.data.model.response.ResponseResetPostEmail
import org.heet.data.model.response.ResponseResetPwd
import org.heet.data.service.ResetService
import org.heet.domain.repository.ResetRepository
import javax.inject.Inject

class ResetRepositoryImpl @Inject constructor(
    private val resetService: ResetService
) : ResetRepository {

    override suspend fun postEmail(): ResponseResetPostEmail {
        return resetService.postEmail()
    }

    override suspend fun resetPwd(requestResetPwd: RequestResetPwd): ResponseResetPwd {
        return resetService.pwdReset(requestResetPwd)
    }
}
