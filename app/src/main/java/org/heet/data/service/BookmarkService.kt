package org.heet.data.service

import org.heet.data.model.response.ResponseGetBookmarkList
import org.heet.data.model.response.ResponsePostBookmark
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkService {

    @POST("/post/save/{id}")
    suspend fun postBookmark(@Path("id") id: String): ResponsePostBookmark

    @GET("/post/save")
    suspend fun getBookmarkList(): ResponseGetBookmarkList
}
