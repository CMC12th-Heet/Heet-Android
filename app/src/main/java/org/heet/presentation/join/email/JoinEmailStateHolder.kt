package org.heet.presentation.join.email

import androidx.compose.runtime.mutableStateOf

class JoinEmailStateHolder {

    var email = mutableStateOf("")
        private set

    var code = mutableStateOf("")
        private set

    var requestCode = mutableStateOf(false)
        private set

    var sendEmail = mutableStateOf(false)
        private set
}
