package org.heet.presentation.signup.pwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.UserInfoRepository
import javax.inject.Inject

@HiltViewModel
class SingUpPwdViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) : ViewModel() {

    fun getEmail(): String = runBlocking {
        userInfoRepository.getEmail()
    }

    fun updatePwd(pwd: String) {
        viewModelScope.launch {
            userInfoRepository.updatePwd(pwd)
        }
    }
}
