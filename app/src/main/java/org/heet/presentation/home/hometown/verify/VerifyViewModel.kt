package org.heet.presentation.home.hometown.verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.repository.PostRepository
import org.heet.domain.repository.VerifyRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val verifyRepository: VerifyRepository
) : ViewModel() {

    private val _verify = MutableStateFlow(false)
    val verify = _verify.asStateFlow()

    private fun updateVerify(verify: Boolean) {
        viewModelScope.launch {
            verifyRepository.updateIsVerify(verify)
        }
    }

    fun postVerify(x: String, y: String) {
        viewModelScope.launch {
            runCatching {
                postRepository.postVerify(x, y)
            }.onSuccess {
                updateVerify(it.message == "success")
                _verify.value = it.message == "success"
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}
