package org.heet.data.service

import org.heet.data.model.request.RequestResetPwd
import org.heet.data.model.response.ResponseResetPostEmail
import org.heet.data.model.response.ResponseResetPwd
import retrofit2.http.Body
import retrofit2.http.POST

interface ResetService {

    @POST("/user/find-password")
    suspend fun postEmail(): ResponseResetPostEmail

    @POST("/user/change-password")
    suspend fun pwdReset(@Body requestResetPwd: RequestResetPwd): ResponseResetPwd
}
