package org.heet.presentation.signup.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.StoreSignUpRepository
import javax.inject.Inject

@HiltViewModel
class SignUpWelcomeViewModel @Inject constructor(
    private val storeSignUpRepository: StoreSignUpRepository
) : ViewModel() {

    fun getId(): String = runBlocking {
        storeSignUpRepository.getId()
    }
}
