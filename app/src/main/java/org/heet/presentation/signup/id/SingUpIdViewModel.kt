package org.heet.presentation.signup.id

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.SignUpRepository
import org.heet.domain.repository.StoreEmailPwdRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SingUpIdViewModel @Inject constructor(
    private val storeEmailPwdRepository: StoreEmailPwdRepository,
    private val singUpRepository: SignUpRepository
) : ViewModel() {

    private val _isDuplicate = MutableStateFlow(false)
    val isDuplicate = _isDuplicate.asStateFlow()

    fun getPwd(): String = runBlocking {
        storeEmailPwdRepository.getPwd()
    }

    fun getEmail(): String = runBlocking {
        storeEmailPwdRepository.getEmail()
    }

    fun postFindDuplicate(username: String) {
        viewModelScope.launch {
            runCatching {
                singUpRepository.findDuplicate(username)
            }.onSuccess {
                _isDuplicate.value = it.isDuplicated
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
