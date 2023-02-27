package org.heet.presentation.signup.residence

import androidx.compose.runtime.mutableStateOf

class SignUpResidenceStateHolder {
    var city = mutableStateOf("")
        private set
    var ward = mutableStateOf("")
        private set
    var checkCity = mutableStateOf(false)
        private set
    var checkWard = mutableStateOf(false)
        private set
}
