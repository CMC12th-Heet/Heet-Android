package org.heet.presentation.home.hometown.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestPostComment
import org.heet.data.model.response.ResponseGetComment
import org.heet.domain.repository.CommentRepository
import org.heet.domain.repository.MyPageRepository
import org.heet.util.ResolutionMetrics
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository,
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    private val _commentList = MutableStateFlow(emptyList<ResponseGetComment>())
    val commentList = _commentList.asStateFlow()

    private val _myId = MutableStateFlow(0)
    val myId = _myId.asStateFlow()

    fun getMyPage() {
        viewModelScope.launch {
            runCatching {
                myPageRepository.getMyPage()
            }.onSuccess {
                _myId.value = it.user_id
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun postComment(postId: String, requestPostComment: RequestPostComment) {
        viewModelScope.launch {
            runCatching {
                commentRepository.postComment(postId, requestPostComment)
            }.onSuccess {
                getComment(postId)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun getComment(postId: String) {
        viewModelScope.launch {
            runCatching {
                commentRepository.getComment(postId)
            }.onSuccess {
                _commentList.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun deleteComment(id: String, postId: String) {
        viewModelScope.launch {
            runCatching {
                commentRepository.deleteComment(id)
            }.onSuccess {
                getComment(postId)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
