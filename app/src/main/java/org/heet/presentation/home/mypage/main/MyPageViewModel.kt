package org.heet.presentation.home.mypage.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.domain.repository.MyPageRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private val _profile = MutableStateFlow<ResponseGetMyPage?>(null)
    val profile = _profile.asStateFlow()

    fun getMyPage() {
        viewModelScope.launch {
            runCatching {
                myPageRepository.getMyPage()
            }.onSuccess {
                _profile.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
