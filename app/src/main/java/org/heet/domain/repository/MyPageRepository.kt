package org.heet.domain.repository

import org.heet.data.model.request.RequestModifyMyPage
import org.heet.data.model.response.*

interface MyPageRepository {

    suspend fun getMyPage(): ResponseGetMyPage
    suspend fun modifyMyPage(requestModifyMyPage: RequestModifyMyPage)
    suspend fun withDraw(): ResponseWithdraw
}
