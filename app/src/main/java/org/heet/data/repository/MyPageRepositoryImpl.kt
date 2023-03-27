package org.heet.data.repository

import org.heet.data.model.request.RequestModifyMyPage
import org.heet.data.model.response.*
import org.heet.data.service.MyPageService
import org.heet.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageService: MyPageService,
) : MyPageRepository {

    override suspend fun getMyPage(): ResponseGetMyPage {
        return myPageService.getMyPage()
    }

    override suspend fun modifyMyPage(requestModifyMyPage: RequestModifyMyPage) {
        return myPageService.modifyMyPage(requestModifyMyPage)
    }

    override suspend fun withDraw(): ResponseWithdraw {
        return myPageService.withdraw()
    }
}
