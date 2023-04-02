package org.heet.data.repository

import org.heet.data.model.request.RequestPostComment
import org.heet.data.model.response.ResponseDeleteComment
import org.heet.data.model.response.ResponseGetComment
import org.heet.data.model.response.ResponsePostComment
import org.heet.data.service.CommentService
import org.heet.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentService: CommentService,
) : CommentRepository {

    override suspend fun postComment(
        id: String,
        requestPostComment: RequestPostComment,
    ): ResponsePostComment {
        return commentService.postComment(id, requestPostComment)
    }

    override suspend fun getComment(id: String): List<ResponseGetComment> {
        return commentService.getComment(id)
    }

    override suspend fun deleteComment(id: String): ResponseDeleteComment {
        return commentService.deleteComment(id)
    }
}
