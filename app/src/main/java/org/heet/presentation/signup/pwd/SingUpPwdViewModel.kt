package org.heet.presentation.signup.pwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

@HiltViewModel
class SingUpPwdViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository
) : ViewModel() {

    fun getEmail(): String = runBlocking {
        storeEmailPwdRepository.getEmail()
    }

    fun updatePwd(pwd: String) {
        viewModelScope.launch {
            storeEmailPwdRepository.updatePwd(pwd)
        }
    }
}
