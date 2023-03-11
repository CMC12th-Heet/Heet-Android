package org.heet.domain.repository

import org.heet.data.model.request.RequestLogin
import org.heet.data.model.response.ResponseLogin

interface LoginRepository {

    suspend fun login(requestLogin: RequestLogin): ResponseLogin
}
