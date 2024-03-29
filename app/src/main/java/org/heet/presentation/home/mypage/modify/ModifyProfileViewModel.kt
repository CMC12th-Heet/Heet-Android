package org.heet.presentation.home.mypage.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestModifyMyPage
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.domain.interfaces.DispatcherInterface
import org.heet.domain.repository.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
    private val verifyRepository: VerifyRepository,
    private val myPageRepository: MyPageRepository,
    private val signUpRepository: SignUpRepository,
    private val dispatcherInterface: DispatcherInterface,
) : ViewModel() {

    private val _seoul = MutableStateFlow(listOf<String>())
    val seoul = _seoul.asStateFlow()
    private val _gyeonggi = MutableStateFlow(listOf<String>())
    val gyeonggi = _gyeonggi.asStateFlow()
    private val _incheon = MutableStateFlow(listOf<String>())
    val incheon = _incheon.asStateFlow()

    private val _withdrawSuccess = MutableStateFlow(false)
    val withdrawSuccess = _withdrawSuccess.asStateFlow()

    private val _modifySuccess = MutableStateFlow(false)
    val modifySuccess = _modifySuccess.asStateFlow()

    private val _profile = MutableStateFlow<ResponseGetMyPage?>(null)
    val profile = _profile.asStateFlow()

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

    fun getMyPage() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                myPageRepository.getMyPage()
            }.onSuccess {
                _profile.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun modifyProfile(username: String, town: String, status: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                myPageRepository.modifyMyPage(RequestModifyMyPage(username, town, status))
            }.onSuccess {
                _modifySuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun deleteUserPreferences() {
        viewModelScope.launch(dispatcherInterface.io) {
            autoLoginRepository.deleteAccessToken()
        }
    }

    fun deleteVerify() {
        viewModelScope.launch(dispatcherInterface.io) {
            verifyRepository.deleteIsVerify()
        }
    }

    fun withdraw() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                myPageRepository.withDraw()
            }.onSuccess {
                deleteVerify()
                deleteUserPreferences()
                _withdrawSuccess.value = it.message == "good"
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
