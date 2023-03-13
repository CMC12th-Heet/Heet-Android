package org.heet.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.VerifyRepository
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val verifyRepository: VerifyRepository
) : ViewModel() {

    fun getIsVerify(): Boolean = runBlocking {
        verifyRepository.getIsVerify()
    }
}
