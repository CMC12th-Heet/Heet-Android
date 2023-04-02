package org.heet.presentation.signup.pwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.UserInfoRepository
import javax.inject.Inject

@HiltViewModel
class SingUpPwdViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    fun getEmail(): String = runBlocking(dispatchers.io) {
        userInfoRepository.getEmail()
    }

    fun updatePwd(pwd: String) {
        viewModelScope.launch(dispatchers.io) {
            userInfoRepository.updatePwd(pwd)
        }
    }
}
