package org.heet.data.service

import org.heet.data.model.request.RequestLogin
import org.heet.data.model.response.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/user/login")
    suspend fun login(@Body requestLogin: RequestLogin): ResponseLogin
}
