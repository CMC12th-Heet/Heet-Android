package org.heet.presentation.join.residence

import androidx.compose.runtime.mutableStateOf

class ResidenceStateHolder {
    var city = mutableStateOf("")
        private set
    var ward = mutableStateOf("")
        private set
    var checkCity = mutableStateOf(false)
        private set
    var checkWard = mutableStateOf(false)
        private set
}
