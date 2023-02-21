package org.heet.presentation.login

import androidx.compose.runtime.mutableStateOf

class LoginStateHolder {

    var idOrEmail = mutableStateOf("")
        private set

    var pwd = mutableStateOf("")
        private set

    var isRegistered = mutableStateOf(true)
        private set
}
