package org.heet.data.service

import org.heet.data.model.request.RequestPost
import org.heet.data.model.request.RequestUpdatePost
import org.heet.data.model.response.*
import retrofit2.http.*

interface PostService {

    @POST("/post")
    suspend fun post(requestPost: RequestPost): ResponsePost

    @POST("/post/verify")
    suspend fun postVerify(
        @Query("x") x: String,
        @Query("y") y: String
    ): ResponsePostVerify

    @GET("/post")
    suspend fun getNewPost(@Query("isNew") isNew: String): List<ResponseGetPost>

    @GET("/post")
    suspend fun getHotPost(@Query("isHot") isHot: String): List<ResponseGetPost>

    @GET("/post/{id}")
    suspend fun getDetailPost(@Path("id") id: String): ResponseGetDetailPost

    @PATCH("/post/{id}")
    suspend fun updatePost(
        @Path("id") id: String,
        requestUpdatePost: RequestUpdatePost
    ): ResponseUpdatePost

    @DELETE("/post/{id}")
    suspend fun deletePost(@Path("id") id: String): ResponseDeletePost
}
