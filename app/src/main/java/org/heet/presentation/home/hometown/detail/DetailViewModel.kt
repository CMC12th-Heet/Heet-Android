package org.heet.presentation.home.hometown.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseGetDetailPost
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.PostRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _detail = MutableStateFlow<ResponseGetDetailPost?>(null)
    val detail = _detail.asStateFlow()

    private val _deleteSuccess = MutableStateFlow(false)
    val deleteSuccess = _deleteSuccess.asStateFlow()

    fun getDetailPost(id: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                postRepository.getDetailPost(id)
            }.onSuccess {
                _detail.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun deletePost(postId: String) {
        viewModelScope.launch {
            runCatching {
                postRepository.deletePost(postId)
            }.onSuccess {
                _deleteSuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
