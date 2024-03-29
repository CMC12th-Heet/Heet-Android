package org.heet.data.service

import org.heet.data.model.request.RequestModifyMyPage
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.data.model.response.ResponseWithdraw
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface MyPageService {

    @GET("/user/my-page")
    suspend fun getMyPage(): ResponseGetMyPage

    @POST("/user/my-page")
    suspend fun modifyMyPage(@Body requestModifyMyPage: RequestModifyMyPage)

    @DELETE("/user")
    suspend fun withdraw(): ResponseWithdraw
}
