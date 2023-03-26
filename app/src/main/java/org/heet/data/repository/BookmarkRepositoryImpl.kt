package org.heet.data.repository

import org.heet.data.model.response.ResponseGetBookmarkList
import org.heet.data.model.response.ResponsePostBookmark
import org.heet.data.service.BookmarkService
import org.heet.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkService: BookmarkService,
) : BookmarkRepository {
    override suspend fun postBookmark(id: String): ResponsePostBookmark {
        return bookmarkService.postBookmark(id)
    }

    override suspend fun getBookmarkList(): ResponseGetBookmarkList {
        return bookmarkService.getBookmarkList()
    }
}
