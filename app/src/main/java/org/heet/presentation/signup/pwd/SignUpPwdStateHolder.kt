package org.heet.presentation.signup.pwd

import androidx.compose.runtime.mutableStateOf

class SignUpPwdStateHolder {

    var pwd = mutableStateOf("")
        private set
    var secondPwd = mutableStateOf("")
        private set
    var isNumber = mutableStateOf(false)
        private set
    var isAlphabet = mutableStateOf(false)
        private set
    var isSpecialChar = mutableStateOf(false)
        private set
    var isValidateLength = mutableStateOf(false)
        private set
    var isValidatePwd = mutableStateOf(false)
        private set
    var checkPwd = mutableStateOf(false)
        private set
    var isHide = mutableStateOf(true)
        private set
    var isSame = mutableStateOf(false)
        private set
}
