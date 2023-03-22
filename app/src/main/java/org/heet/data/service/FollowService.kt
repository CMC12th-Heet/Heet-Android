package org.heet.data.service

import org.heet.data.model.response.ResponseGetFollower
import org.heet.data.model.response.ResponseGetFollowing
import org.heet.data.model.response.ResponsePostFollow
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowService {

    @GET("/user/follow/{id}")
    suspend fun postFollow(@Path("id") id: String): ResponsePostFollow

    @GET("/user/follower")
    suspend fun getFollower(): List<ResponseGetFollower>

    @GET("/user/following")
    suspend fun getFollowing(): List<ResponseGetFollowing>
}
