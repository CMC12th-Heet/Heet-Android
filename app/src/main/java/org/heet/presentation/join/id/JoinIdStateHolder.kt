package org.heet.presentation.join.id

import androidx.compose.runtime.mutableStateOf

class JoinIdStateHolder {

    var id = mutableStateOf("")
        private set

    var isDuplicate = mutableStateOf(true)
        private set

    var requestCheckDuplicate = mutableStateOf(false)
        private set
}
