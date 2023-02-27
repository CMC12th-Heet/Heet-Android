package org.heet.presentation.signup.id

import androidx.compose.runtime.mutableStateOf

class SignUpIdStateHolder {

    var id = mutableStateOf("")
        private set

    var isDuplicate = mutableStateOf(true)
        private set

    var requestCheckDuplicate = mutableStateOf(false)
        private set
}
