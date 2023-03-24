package org.heet.data.repository

import org.heet.data.model.response.ResponseFollowingPost
import org.heet.data.model.response.ResponseGetFollower
import org.heet.data.model.response.ResponseGetFollowing
import org.heet.data.model.response.ResponsePostFollow
import org.heet.data.service.FollowService
import org.heet.domain.repository.FollowRepository
import javax.inject.Inject

class FollowRepositoryImpl @Inject constructor(
    private val followService: FollowService,
) : FollowRepository {

    override suspend fun postFollow(id: String): ResponsePostFollow {
        return followService.postFollow(id)
    }

    override suspend fun getFollower(): List<ResponseGetFollower> {
        return followService.getFollower()
    }

    override suspend fun getFollowing(): List<ResponseGetFollowing> {
        return followService.getFollowing()
    }

    override suspend fun getFollowingPost(): ResponseFollowingPost {
        return followService.getFollowingPost()
    }
}
