package org.heet.data.service

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.response.ResponseFindDuplicate
import org.heet.data.model.response.ResponsePostEmail
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpService {

    @POST("/user/email-verify")
    suspend fun postEmail(@Body requestPostEmail: RequestPostEmail): ResponsePostEmail

    @POST("/user/find-duplicate")
    suspend fun postFindDuplicate(@Query("username") username: String): ResponseFindDuplicate
}
