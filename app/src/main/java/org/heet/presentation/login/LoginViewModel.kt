package org.heet.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestLogin
import org.heet.domain.repository.AutoLoginRepository
import org.heet.domain.repository.LoginRepository
import org.heet.domain.repository.UserInfoRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
    private val loginRepository: LoginRepository,
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _registered = MutableStateFlow(true)
    val registered = _registered.asStateFlow()

    fun setRegisterTrue() {
        _registered.value = true
    }

    fun deleteAll() {
        viewModelScope.launch {
            userInfoRepository.deleteAll()
        }
    }

    fun login(requestLogin: RequestLogin) {
        viewModelScope.launch {
            runCatching {
                loginRepository.login(requestLogin)
            }.onSuccess {
                _loginSuccess.value = true
                autoLoginRepository.updateAccessToken(it.toLoginInfo().token)
            }.onFailure {
                _registered.value = false
                Timber.d(it.message)
            }
        }
    }
}
