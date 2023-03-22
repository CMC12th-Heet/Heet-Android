package org.heet.data.service

import org.heet.data.model.request.RequestPostComment
import org.heet.data.model.response.ResponseDeleteComment
import org.heet.data.model.response.ResponseGetComment
import org.heet.data.model.response.ResponsePostComment
import retrofit2.http.*

interface CommentService {

    @POST("/comment/{id}")
    suspend fun postComment(
        @Path("id") id: String,
        @Body requestPostComment: RequestPostComment
    ): ResponsePostComment

    @GET("/comment/{id}")
    suspend fun getComment(
        @Path("id") id: String
    ): List<ResponseGetComment>

    @DELETE("/comment/{id}")
    suspend fun deleteComment(
        @Path("id") id: String
    ): ResponseDeleteComment
}
