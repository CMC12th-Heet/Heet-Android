package org.heet.domain.repository

import org.heet.data.model.request.RequestUpdatePost
import org.heet.data.model.response.*

interface PostRepository {
    suspend fun postVerify(x: String, y: String): ResponsePostVerify
    suspend fun getNewPost(isNew: String): List<ResponseGetPost>
    suspend fun getHotPost(isHot: String): List<ResponseGetPost>
    suspend fun getDetailPost(id: String): ResponseGetDetailPost
    suspend fun updatePost(id: String, requestUpdatePost: RequestUpdatePost): ResponseUpdatePost
    suspend fun deletePost(id: String): ResponseDeletePost
}
