package org.heet.data.service

import org.heet.data.model.response.ResponsePostFollow
import retrofit2.http.POST
import retrofit2.http.Path

interface FollowService {

    @POST("/user/follow/{id}")
    suspend fun postFollow(@Path("id") id: String): ResponsePostFollow
}
