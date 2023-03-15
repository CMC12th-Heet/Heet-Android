package org.heet.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginInfo(
    val token: String
) : Parcelable
