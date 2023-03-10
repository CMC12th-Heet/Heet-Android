package org.heet.data.service

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.response.ResponsePostEmail
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("/user/email-verify")
    suspend fun postEmail(@Body requestPostEmail: RequestPostEmail): ResponsePostEmail
}
