package org.heet.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        autoLoginRepository.accessToken.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}
