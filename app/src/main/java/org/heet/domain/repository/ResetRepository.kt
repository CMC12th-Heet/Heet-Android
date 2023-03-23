package org.heet.domain.repository

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.request.RequestResetPwd
import org.heet.data.model.response.ResponseResetPostEmail
import org.heet.data.model.response.ResponseResetPwd

interface ResetRepository {

    suspend fun postEmail(requestPostEmail: RequestPostEmail): ResponseResetPostEmail

    suspend fun resetPwd(requestResetPwd: RequestResetPwd): ResponseResetPwd
}
