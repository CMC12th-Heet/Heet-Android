package org.heet.presentation.resetpassword

import androidx.compose.runtime.mutableStateOf

class ResetPwdStateHolder {

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

    fun setContainNumber(containNumber: Boolean) {
        this.isNumber.value = containNumber
    }

    fun setContainAlphabet(containAlphabet: Boolean) {
        this.isAlphabet.value = containAlphabet
    }

    fun setContainSpecialCharacters(containSpecialCharacters: Boolean) {
        this.isSpecialChar.value = containSpecialCharacters
    }

    fun setValidateLength(validateLength: Boolean) {
        this.isValidateLength.value = validateLength
    }

    fun setValidatePwd(validatePwd: Boolean) {
        this.isValidatePwd.value = validatePwd
    }

    fun setCheckPassword(checkPassword: Boolean) {
        this.checkPwd.value = checkPassword
    }

    fun setIsHide() {
        isHide.value = !isHide.value
    }

    fun setIsSame(isSame: Boolean) {
        this.isSame.value = isSame
    }
}
