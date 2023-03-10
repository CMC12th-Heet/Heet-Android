package org.heet.domain.repository

import org.heet.data.model.request.RequestPostEmail
import org.heet.data.model.response.ResponsePostEmail

interface SignUpRepository {
    suspend fun postEmail(requestPostEmail: RequestPostEmail): ResponsePostEmail
}
