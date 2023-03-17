package org.heet.presentation.home.mypage.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import org.heet.domain.repository.MyPageRepository
import org.heet.domain.repository.VerifyRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
    private val verifyRepository: VerifyRepository,
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private val _withdrawSuccess = MutableStateFlow(false)
    val withdrawSuccess = _withdrawSuccess.asStateFlow()

    fun deleteUserPreferences() {
        viewModelScope.launch {
            autoLoginRepository.deleteAccessToken()
        }
    }

    fun deleteVerify() {
        viewModelScope.launch {
            verifyRepository.deleteIsVerify()
        }
    }

    fun withdraw() {
        viewModelScope.launch {
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
