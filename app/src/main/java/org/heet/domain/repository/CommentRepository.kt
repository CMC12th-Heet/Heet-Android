package org.heet.domain.repository

import org.heet.data.model.request.RequestPostComment
import org.heet.data.model.response.ResponseDeleteComment
import org.heet.data.model.response.ResponseGetComment
import org.heet.data.model.response.ResponsePostComment

interface CommentRepository {

    suspend fun postComment(id: String, requestPostComment: RequestPostComment): ResponsePostComment
    suspend fun getComment(id: String): List<ResponseGetComment>
    suspend fun deleteComment(id: String): ResponseDeleteComment
}
