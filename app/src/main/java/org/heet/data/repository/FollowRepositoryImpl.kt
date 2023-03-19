package org.heet.data.repository

import org.heet.data.model.response.ResponsePostFollow
import org.heet.data.service.FollowService
import org.heet.domain.repository.FollowRepository
import javax.inject.Inject

class FollowRepositoryImpl @Inject constructor(
    private val followService: FollowService
) : FollowRepository {

    override suspend fun postFollow(id: String): ResponsePostFollow {
        return followService.postFollow(id)
    }
}
