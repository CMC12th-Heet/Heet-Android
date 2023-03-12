package org.heet.data.service

import org.heet.data.model.request.RequestModifyMyPage
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.data.model.response.ResponseModifyMyPage
import retrofit2.http.GET
import retrofit2.http.POST

interface MyPageService {

    @GET("/user/my-page")
    suspend fun getMyPage(): ResponseGetMyPage

    @POST("/user/my-page")
    suspend fun modifyMyPage(requestModifyMyPage: RequestModifyMyPage): ResponseModifyMyPage
}
