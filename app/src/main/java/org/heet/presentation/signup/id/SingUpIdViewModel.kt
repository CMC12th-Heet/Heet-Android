package org.heet.presentation.signup.id

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.SignUpRepository
import org.heet.domain.repository.UserInfoRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SingUpIdViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val singUpRepository: SignUpRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _isDuplicate = MutableStateFlow(true)
    val isDuplicate = _isDuplicate.asStateFlow()

    fun getPwd(): String = runBlocking {
        userInfoRepository.getPwd()
    }

    fun getEmail(): String = runBlocking {
        userInfoRepository.getEmail()
    }

    fun postFindDuplicate(username: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                singUpRepository.findDuplicate(username)
            }.onSuccess {
                userInfoRepository.updateId(username)
                _isDuplicate.value = true
            }.onFailure {
                _isDuplicate.value = false
                Timber.d(it.message)
            }
        }
    }
}
