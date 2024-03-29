package org.heet.presentation.home.following.followinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetFollowing
import org.heet.domain.interfaces.DispatcherInterface
import org.heet.domain.repository.FollowRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowingListViewModel @Inject constructor(
    private val followRepository: FollowRepository,
    private val dispatcherInterface: DispatcherInterface,
) : ViewModel() {

    private val _followingList = MutableStateFlow(emptyList<ResponseGetFollowing>())
    val followingList = _followingList.asStateFlow()

    fun postFollow(id: String) {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                followRepository.postFollow(id)
            }.onSuccess {
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getFollowingList() {
        viewModelScope.launch(dispatcherInterface.io) {
            runCatching {
                followRepository.getFollowing()
            }.onSuccess {
                _followingList.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
