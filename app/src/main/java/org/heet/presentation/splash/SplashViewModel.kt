package org.heet.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val autoLoginRepository: AutoLoginRepository) :
    ViewModel() {

    fun getToken(): String = runBlocking {
        autoLoginRepository.getToken()
    }
}
