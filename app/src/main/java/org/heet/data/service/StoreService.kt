package org.heet.data.service

import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.ResponseGetStore
import org.heet.data.model.response.ResponsePostStore
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StoreService {

    @POST("/store")
    suspend fun postStore(@Body requestPostStore: RequestPostStore): ResponsePostStore

    @GET("/store")
    suspend fun getStore(@Query("keyword") keyword: String): List<ResponseGetStore>
}
