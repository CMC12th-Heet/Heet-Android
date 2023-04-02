package org.heet.presentation.signup.residence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.interfaces.DispatcherInterface
import org.heet.domain.repository.SignUpRepository
import org.heet.domain.repository.UserInfoRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ResidenceViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val userInfoRepository: UserInfoRepository,
    private val dispatcherInterface: DispatcherInterface,
) : ViewModel() {

    private val _seoul = MutableStateFlow(listOf<String>())
    val seoul = _seoul.asStateFlow()
    private val _gyeonggi = MutableStateFlow(listOf<String>())
    val gyeonggi = _gyeonggi.asStateFlow()
    private val _incheon = MutableStateFlow(listOf<String>())
    val incheon = _incheon.asStateFlow()

    fun updateResidence(residence: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            userInfoRepository.updateHometown(residence)
        }
    }

    fun getSeoulCity(name: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                signUpRepository.getSeoulCity(name)
            }.onSuccess {
                _seoul.value = it.seoul
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getGyeonggiCity(name: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                signUpRepository.getGyeonggin(name)
            }.onSuccess {
                _gyeonggi.value = it.gyeonggi
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getIncheonCity(name: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                signUpRepository.getIncheon(name)
            }.onSuccess {
                _incheon.value = it.incheon
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
