package org.heet.presentation.home.hometown.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.data.model.response.ResponseGetPost
import org.heet.domain.interfaces.DispatcherInterface
import org.heet.domain.repository.MyPageRepository
import org.heet.domain.repository.PostRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeTownViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val myPageRepository: MyPageRepository,
    private val dispatcherInterface: DispatcherInterface,
) : ViewModel() {

    private val _newPost = MutableStateFlow<ResponseGetPost?>(null)
    val newPost = _newPost.asStateFlow()

    private val _cityPost = MutableStateFlow<ResponseGetPost?>(null)
    val cityPost = _cityPost.asStateFlow()

    private val _isNewPost = MutableStateFlow(false)
    val isNewPost = _isNewPost.asStateFlow()

    private val _hotPost = MutableStateFlow<ResponseGetPost?>(null)
    val hotPost = _hotPost.asStateFlow()

    private val _profile = MutableStateFlow<ResponseGetMyPage?>(null)
    val profile = _profile.asStateFlow()

    private val _town = MutableStateFlow("")
    val town = _town.asStateFlow()

    fun getMyPage() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                myPageRepository.getMyPage()
            }.onSuccess {
                _profile.value = it
                _town.value = it.town
                getCityPost()
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getNewPost() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                postRepository.getNewPost("0")
            }.onSuccess {
                _newPost.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getCityPost() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                postRepository.getCityPost(town.value)
            }.onSuccess {
                _cityPost.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getHotPost() {
        viewModelScope.launch(dispatcherInterface.io) {
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
