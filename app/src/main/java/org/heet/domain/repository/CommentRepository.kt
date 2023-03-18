package org.heet.domain.repository

import org.heet.data.model.request.RequestPostComment
import org.heet.data.model.response.ResponseDeleteComment
import org.heet.data.model.response.ResponseGetComment

interface CommentRepository {

    suspend fun postComment(id: String, requestPostComment: RequestPostComment)
    suspend fun getComment(id: String): List<ResponseGetComment>
    suspend fun deleteComment(id: String): ResponseDeleteComment
}
