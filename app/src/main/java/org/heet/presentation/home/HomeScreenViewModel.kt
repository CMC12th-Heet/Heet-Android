package org.heet.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.MyPageRepository
import org.heet.domain.repository.VerifyRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val verifyRepository: VerifyRepository,
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private fun setVerify(isVerify: Boolean) {
        viewModelScope.launch {
            verifyRepository.updateIsVerify(isVerify)
        }
    }

    fun getIsVerify(): Boolean = runBlocking {
        verifyRepository.getIsVerify()
    }

    fun getMyPage() {
        viewModelScope.launch {
            runCatching {
                myPageRepository.getMyPage()
            }.onSuccess {
                setVerify(it.is_verify)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
