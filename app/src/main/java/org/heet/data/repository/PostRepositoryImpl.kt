package org.heet.data.repository

import org.heet.data.model.request.RequestPost
import org.heet.data.model.request.RequestUpdatePost
import org.heet.data.model.response.*
import org.heet.data.service.PostService
import org.heet.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {
    override suspend fun post(requestPost: RequestPost): ResponsePost {
        return postService.post(requestPost)
    }

    override suspend fun postVerify(x: String, y: String): ResponsePostVerify {
        return postService.postVerify(x, y)
    }

    override suspend fun getNewPost(isNew: String): List<ResponseGetPost> {
        return postService.getNewPost(isNew)
    }

    override suspend fun getHotPost(isHot: String): List<ResponseGetPost> {
        return postService.getHotPost(isHot)
    }

    override suspend fun getDetailPost(id: String): ResponseGetDetailPost {
        return postService.getDetailPost(id)
    }

    override suspend fun updatePost(
        id: String,
        requestUpdatePost: RequestUpdatePost
    ): ResponseUpdatePost {
        return postService.updatePost(id, requestUpdatePost)
    }

    override suspend fun deletePost(id: String): ResponseDeletePost {
        return postService.deletePost(id)
    }
}
