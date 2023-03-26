package org.heet.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.heet.data.model.request.RequestUpdatePost
import org.heet.data.model.response.*
import retrofit2.http.*

interface PostService {

    @Multipart
    @POST("/post")
    suspend fun post(
        @Part files: List<MultipartBody.Part?>,
        @Part("title") title: RequestBody,
        @Part("mini_title") mini_title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("store_id") store_id: RequestBody,
        @Part("satisfaction") satisfaction: RequestBody?,
        @Part("together_with") together_with: RequestBody?,
        @Part("perfect_day") perfect_day: RequestBody?,
        @Part("moving_tip") moving_tip: RequestBody?,
        @Part("ordering_tip") ordering_tip: RequestBody?,
        @Part("other_tips") other_tips: RequestBody?,
    ): ResponsePost

    @POST("/post/verify")
    suspend fun postVerify(
        @Query("x") x: String,
        @Query("y") y: String,
    ): ResponsePostVerify

    @GET("/post")
    suspend fun getNewPost(@Query("isNew") isNew: String): ResponseGetPost

    @GET("/post")
    suspend fun getCityPost(@Query("city") city: String): ResponseGetPost

    @GET("/post")
    suspend fun getHotPost(@Query("isHot") isHot: String): ResponseGetPost

    @GET("/post/{id}")
    suspend fun getDetailPost(@Path("id") id: String): ResponseGetDetailPost

    @PATCH("/post/{id}")
    suspend fun updatePost(
        @Path("id") id: String,
        @Body requestUpdatePost: RequestUpdatePost,
    ): ResponseUpdatePost

    @DELETE("/post/{id}")
    suspend fun deletePost(@Path("id") id: String): ResponseDeletePost
}
