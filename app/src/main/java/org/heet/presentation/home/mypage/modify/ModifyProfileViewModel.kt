package org.heet.presentation.home.mypage.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.heet.domain.repository.AutoLoginRepository
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(private val autoLoginRepository: AutoLoginRepository) :
    ViewModel() {

    fun deleteUserPreferences() {
        viewModelScope.launch {
            autoLoginRepository.deletePreferencesDidLogin()
        }
    }
}
