package org.heet.presentation.signup.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.data.model.request.RequestPostSignUp
import org.heet.domain.interfaces.DispatcherInterface
import org.heet.domain.repository.SignUpRepository
import org.heet.domain.repository.UserInfoRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val signUpRepository: SignUpRepository,
    private val dispatcherInterface: DispatcherInterface,
) : ViewModel() {

    private val _signUp = MutableStateFlow(false)
    val signUp = _signUp.asStateFlow()

    fun signUp() {
        viewModelScope.launch(dispatcherInterface.io) {
            kotlin.runCatching {
                signUpRepository.signUp(
                    RequestPostSignUp(
                        userInfoRepository.getEmail(),
                        userInfoRepository.getId(),
                        userInfoRepository.getPwd(),
                        userInfoRepository.getHometown(),
                    ),
                )
            }.onSuccess {
                _signUp.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getResidence(): String = runBlocking(dispatcherInterface.io) {
        userInfoRepository.getHometown()
    }

    fun getUsername(): String = runBlocking(dispatcherInterface.io) {
        userInfoRepository.getId()
    }
}
