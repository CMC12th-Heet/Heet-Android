package org.heet.presentation.home.following.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseFollowingPost
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.FollowRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val followRepository: FollowRepository,
    private val dispatchers: DispatcherProvider,
    ) : ViewModel() {

    private val _followingPosts = MutableStateFlow<ResponseFollowingPost?>(null)
    val followingPosts = _followingPosts.asStateFlow()

    fun getFollowingPost() {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                followRepository.getFollowingPost()
            }.onSuccess {
                _followingPosts.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
