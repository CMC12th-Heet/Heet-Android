package org.heet.presentation.home.hometown.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.ResponseGetStore
import org.heet.domain.repository.AddressRepository
import org.heet.domain.repository.StoreRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _store = MutableStateFlow(emptyList<ResponseGetStore>())
    val store = _store.asStateFlow()

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess = _updateSuccess.asStateFlow()

    fun searchStore(keyword: String) {
        viewModelScope.launch {
            runCatching {
                storeRepository.getStore(keyword)
            }.onSuccess {
                _store.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun postStore(requestPostStore: RequestPostStore) {
        viewModelScope.launch {
            runCatching {
                storeRepository.postStore(requestPostStore)
            }.onSuccess {
                updateSelectStoreNum(it)
                updateSelectStore(requestPostStore.name)
                _updateSuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    private fun updateSelectStore(storeName: String) {
        viewModelScope.launch {
            addressRepository.updateSelectStore(storeName)
        }
    }

    private fun updateSelectStoreNum(storeNum: Int) {
        viewModelScope.launch {
            addressRepository.updateSelectStoreNum(storeNum)
        }
    }
}
