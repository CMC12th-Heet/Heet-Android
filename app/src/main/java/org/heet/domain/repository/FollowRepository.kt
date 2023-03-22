package org.heet.domain.repository

import org.heet.data.model.response.ResponseGetFollower
import org.heet.data.model.response.ResponseGetFollowing
import org.heet.data.model.response.ResponsePostFollow

interface FollowRepository {

    suspend fun postFollow(id: String): ResponsePostFollow

    suspend fun getFollower(): List<ResponseGetFollower>

    suspend fun getFollowing(): List<ResponseGetFollowing>
}
