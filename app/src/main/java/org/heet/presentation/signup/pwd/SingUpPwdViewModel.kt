package org.heet.presentation.signup.pwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

@HiltViewModel
class SingUpPwdViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    init {
        viewModelScope.launch {
            storeEmailPwdRepository.getEmail().collect() { email ->
                _email.value = email
            }
        }
    }

    fun updatePwd(pwd: String) {
        viewModelScope.launch {
            storeEmailPwdRepository.updatePwd(pwd)
        }
    }
}
