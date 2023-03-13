package org.heet.presentation.home.mypage.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import org.heet.domain.repository.VerifyRepository
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val autoLoginRepository: AutoLoginRepository,
    private val verifyRepository: VerifyRepository
) : ViewModel() {
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
}
