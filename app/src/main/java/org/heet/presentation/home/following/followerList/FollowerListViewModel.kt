package org.heet.presentation.home.following.followerList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetFollower
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.FollowRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowerListViewModel @Inject constructor(
    private val followRepository: FollowRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _followerList = MutableStateFlow(emptyList<ResponseGetFollower>())
    val followerList = _followerList.asStateFlow()

    fun postFollow(id: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                followRepository.postFollow(id)
            }.onSuccess {
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getFollowerList() {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                followRepository.getFollower()
            }.onSuccess {
                _followerList.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
