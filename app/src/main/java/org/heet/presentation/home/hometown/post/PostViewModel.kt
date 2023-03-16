package org.heet.presentation.home.hometown.post

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.ResponseGetStore
import org.heet.data.service.PostService
import org.heet.domain.repository.AddressRepository
import org.heet.domain.repository.StoreRepository
import org.heet.util.ContentUriRequestBody
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
    private val postService: PostService,
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _postSuccess = MutableStateFlow(false)
    val postSuccess = _postSuccess.asStateFlow()

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

    fun getSelectStore(): String = runBlocking {
        addressRepository.getSelectStore()
    }

    fun getSelectStoreNum(): Int = runBlocking {
        addressRepository.getSelectStoreNum()
    }

    fun deleteSelectStore() {
        viewModelScope.launch {
            addressRepository.deleteSelectStore()
        }
    }

    fun deleteSelectStoreNum() {
        viewModelScope.launch {
            addressRepository.deleteSelectStoreNum()
        }
    }

    fun post(
        context: Context,
        image: List<Uri?>,
        title: String,
        mini_title: String,
        content: String,
        storeId: Int
    ) {
        val imageListMultipartBody = mutableListOf<MultipartBody.Part>()
        val titleRequestBody = title.toPlainRequestBody()
        val miniTitleRequestBody = mini_title.toPlainRequestBody()
        val contentRequestBody = content.toPlainRequestBody()
        val storeIdRequestBody = storeId.toString().toPlainRequestBody()

        for (element in image) {
            val imageMultipartBody: MultipartBody.Part =
                ContentUriRequestBody(context, element ?: continue).toFormData()
            imageListMultipartBody.add(imageMultipartBody)
        }

        viewModelScope.launch {
            runCatching {
                postService.post(
                    imageListMultipartBody,
                    titleRequestBody,
                    miniTitleRequestBody,
                    contentRequestBody,
                    storeIdRequestBody,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            }.onSuccess {
                deleteSelectStore()
                deleteSelectStoreNum()
                _postSuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    private fun String?.toPlainRequestBody() =
        requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())
}
