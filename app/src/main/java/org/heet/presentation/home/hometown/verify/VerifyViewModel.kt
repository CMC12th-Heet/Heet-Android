package org.heet.presentation.home.hometown.verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.PostRepository
import org.heet.domain.repository.VerifyRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val verifyRepository: VerifyRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _isProperLocation = MutableStateFlow(true)
    val isProperLocation = _isProperLocation.asStateFlow()

    private val _isVerifySuccess = MutableStateFlow(false)
    val isVerifySuccess = _isVerifySuccess.asStateFlow()

    private fun updateVerify(verify: Boolean) {
        viewModelScope.launch(dispatchers.io) {
            verifyRepository.updateIsVerify(verify)
        }
    }

    fun postVerify(latitude: String, longitude: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                postRepository.postVerify(latitude, longitude)
            }.onSuccess {
                updateVerify(it.message == "success")
                if (it.message == "fail") {
                    _isProperLocation.value = false
                }
                _isVerifySuccess.value = it.message == "success"
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}
