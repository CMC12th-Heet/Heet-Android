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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    fun login(requestLogin: RequestLogin) {
        viewModelScope.launch {
            runCatching {
                loginRepository.login(requestLogin)
            }.onSuccess {
                _loginSuccess.value = true
                autoLoginRepository.updateAccessToken(it.token)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
