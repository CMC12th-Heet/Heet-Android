package org.heet.data.service

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpService {

    @POST("/user/email-verify")
    suspend fun postEmail(@Body requestPostEmail: RequestPostEmail): ResponsePostEmail

    @POST("/user/find-duplicate")
    suspend fun postFindDuplicate(@Query("username") username: String): ResponseFindDuplicate

    @GET("/user/city")
    suspend fun getSeoulCity(@Query("name") name: String): ResponseSeoulCity

    @GET("/user/city")
    suspend fun getGyeonggin(@Query("name") name: String): ResponseGyeonggiCity

    @GET("/user/city")
    suspend fun getIncheon(@Query("name") name: String): ResponseIncheonCity
}
