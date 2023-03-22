package org.heet.presentation.home.hometown.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.data.model.response.ResponseGetPost
import org.heet.domain.repository.MyPageRepository
import org.heet.domain.repository.PostRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeTownViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private val _newPost = MutableStateFlow(emptyList<ResponseGetPost>())
    val newPost = _newPost.asStateFlow()

    private val _isNewPost = MutableStateFlow(false)
    val isNewPost = _isNewPost.asStateFlow()

    private val _hotPost = MutableStateFlow(emptyList<ResponseGetPost>())
    val hotPost = _hotPost.asStateFlow()

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

    fun getNewPost() {
        viewModelScope.launch {
            runCatching {
                postRepository.getNewPost("0")
            }.onSuccess {
                _newPost.value = it
                _isNewPost.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getHotPost() {
        viewModelScope.launch {
            runCatching {
                postRepository.getHotPost("0")
            }.onSuccess {
                _hotPost.value = it
                _isNewPost.value = false
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
