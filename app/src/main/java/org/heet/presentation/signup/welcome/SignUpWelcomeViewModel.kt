package org.heet.presentation.signup.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

@HiltViewModel
class SignUpWelcomeViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository
) : ViewModel() {

    fun getId(): String = runBlocking {
        storeEmailPwdRepository.getId()
    }
}
