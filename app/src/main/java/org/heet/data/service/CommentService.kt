package org.heet.data.service

import org.heet.data.model.response.ResponseDeleteComment
import org.heet.data.model.response.ResponseGetComment
import org.heet.data.model.response.ResponsePostComment
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {

    @POST("/comment/{id}")
    suspend fun postComment(
        @Query("id") id: String,
        @Body content: String
    ): ResponsePostComment

    @GET("/comment/{id}")
    suspend fun getComment(
        @Query("id") id: String
    ): ResponseGetComment

    @DELETE("/comment/{id}")
    suspend fun deleteComment(
        @Query("id") id: String
    ): ResponseDeleteComment
}
