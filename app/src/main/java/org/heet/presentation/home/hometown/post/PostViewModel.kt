package org.heet.presentation.home.hometown.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.heet.domain.repository.AddressRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {

    fun getSelectStore(): String = runBlocking {
        addressRepository.getSelectStore()
    }

    fun deleteSelectStore() {
        viewModelScope.launch {
            addressRepository.deleteSelectStore()
        }
    }
}
