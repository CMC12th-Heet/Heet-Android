package org.heet.presentation.findpwd

import androidx.compose.runtime.mutableStateOf

class FindPwdStateHolder {
    var email = mutableStateOf("")
        private set

    var code = mutableStateOf("")
        private set

    var requestEmail = mutableStateOf(false)
        private set

    var requestCode = mutableStateOf(false)
        private set
}
