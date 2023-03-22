package org.heet.data.service

import org.heet.data.model.response.ResponseUserSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/user/search")
    suspend fun searchUser(@Query("keyword") keyword: String): List<ResponseUserSearch>
}
