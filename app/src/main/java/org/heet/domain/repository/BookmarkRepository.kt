package org.heet.domain.repository

import org.heet.data.model.response.ResponseGetBookmarkList
import org.heet.data.model.response.ResponsePostBookmark

interface BookmarkRepository {

    suspend fun postBookmark(id: String): ResponsePostBookmark

    suspend fun getBookmarkList(): ResponseGetBookmarkList
}
