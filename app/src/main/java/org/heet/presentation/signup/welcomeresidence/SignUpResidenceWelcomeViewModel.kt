package org.heet.presentation.signup.welcomeresidence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.data.model.request.RequestPostSignUp
import org.heet.domain.repository.SignUpRepository
import org.heet.domain.repository.StoreEmailPwdRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpResidenceWelcomeViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository,
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _signUp = MutableStateFlow(false)
    val signUp = _signUp.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                signUpRepository.signUp(
                    RequestPostSignUp(
                        storeEmailPwdRepository.getEmail(),
                        storeEmailPwdRepository.getId(),
                        storeEmailPwdRepository.getPwd(),
                        storeEmailPwdRepository.getHometown()
                    )
                )
            }.onSuccess {
                _signUp.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getResidence(): String = runBlocking {
        storeEmailPwdRepository.getHometown()
    }

    fun getUsername(): String = runBlocking {
        storeEmailPwdRepository.getId()
    }
}
