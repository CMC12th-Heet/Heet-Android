package org.heet.presentation.signup.email

import androidx.compose.runtime.mutableStateOf

class SignUpEmailStateHolder {

    var email = mutableStateOf("")
        private set

    var code = mutableStateOf("")
        private set

    var requestCode = mutableStateOf(false)
        private set

    var sendEmail = mutableStateOf(false)
        private set
}
