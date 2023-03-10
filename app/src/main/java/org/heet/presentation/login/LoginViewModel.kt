package org.heet.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val autoLoginRepository: AutoLoginRepository) :
    ViewModel() {

    fun updateUserPreferences() {
        viewModelScope.launch {
            autoLoginRepository.updateDidLogin()
        }
    }
}
