package org.heet.presentation.forgot.email

import androidx.compose.runtime.mutableStateOf

class ForgotEmailHolder {
    var email = mutableStateOf("")
        private set

    var code = mutableStateOf("")
        private set

    var requestEmail = mutableStateOf(false)
        private set

    var requestCode = mutableStateOf(false)
        private set
}
