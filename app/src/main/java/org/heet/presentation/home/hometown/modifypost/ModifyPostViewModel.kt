package org.heet.presentation.home.hometown.modifypost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestUpdatePost
import org.heet.data.model.response.ResponseGetDetailPost
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.PostRepository
import org.heet.util.ResolutionMetrics
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ModifyPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    private val _postContent = MutableStateFlow<ResponseGetDetailPost?>(null)
    val postContent = _postContent.asStateFlow()

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess = _updateSuccess.asStateFlow()

    fun getDetailPost(id: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                postRepository.getDetailPost(id)
            }.onSuccess {
                _postContent.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun updatePost(
        id: String,
        title: String,
        mini_title: String,
        content: String,
        satisfaction: Int? = null,
        togetherWith: String?,
        dayTip: String?,
        movingTip: String?,
        orderingTip: String?,
        otherTip: String?,
    ) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                postRepository.updatePost(
                    id,
                    RequestUpdatePost(
                        title,
                        mini_title,
                        content,
                        satisfaction,
                        togetherWith,
                        dayTip,
                        movingTip,
                        orderingTip,
                        otherTip,
                    ),
                )
            }.onSuccess {
                _updateSuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
