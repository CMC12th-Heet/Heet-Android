package org.heet.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val autoLoginRepository: AutoLoginRepository) :
    ViewModel() {

    private val _didLogin = MutableStateFlow(false)
    val didLogin = _didLogin.asStateFlow()

    init {
        viewModelScope.launch {
            autoLoginRepository.getPreferencesDidLogin().collect() {
                _didLogin.value = it
            }
        }
    }
}
