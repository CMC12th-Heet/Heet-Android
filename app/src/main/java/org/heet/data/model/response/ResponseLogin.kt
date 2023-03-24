package org.heet.data.model.response

import kotlinx.serialization.Serializable
import org.heet.domain.entity.LoginInfo

@Serializable
data class ResponseLogin(
    val message: String,
    val token: String,
    val town: String,
) {
    fun toLoginInfo() = LoginInfo(
        token,
        town,
    )
}
