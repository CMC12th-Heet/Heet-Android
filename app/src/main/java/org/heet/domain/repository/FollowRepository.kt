package org.heet.domain.repository

import org.heet.data.model.response.ResponsePostFollow

interface FollowRepository {

    suspend fun postFollow(id: String): ResponsePostFollow
}
