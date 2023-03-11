package org.heet.presentation.signup.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

@HiltViewModel
class SignUpResidenceWelcomeViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository
) : ViewModel() {

    fun getEmail(): String = runBlocking {
        storeEmailPwdRepository.getEmail()
    }

    fun getResidence(): String = runBlocking {
        storeEmailPwdRepository.getHometown()
    }

    fun getUsername(): String = runBlocking {
        storeEmailPwdRepository.getId()
    }

    fun getPwd(): String = runBlocking {
        storeEmailPwdRepository.getPwd()
    }
}
