package org.heet.data.repository

import org.heet.data.model.request.RequestLogin
import org.heet.data.model.response.ResponseLogin
import org.heet.data.service.LoginService
import org.heet.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginService: LoginService) :
    LoginRepository {

    override suspend fun login(requestLogin: RequestLogin): ResponseLogin {
        return loginService.login(requestLogin)
    }
}
