package org.heet.presentation.home.hometown.finduser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.response.ResponseUserSearch
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.SearchRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FindUserViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _searchUserList = MutableStateFlow(emptyList<ResponseUserSearch>())
    val searchUserList = _searchUserList.asStateFlow()

    fun searchUser(keyword: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                searchRepository.userSearch(keyword)
            }.onSuccess {
                _searchUserList.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
