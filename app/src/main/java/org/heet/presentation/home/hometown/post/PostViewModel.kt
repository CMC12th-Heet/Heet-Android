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
import org.heet.data.provider.DispatcherProvider
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
    private val storeRepository: StoreRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _postSuccess = MutableStateFlow(false)
    val postSuccess = _postSuccess.asStateFlow()

    private val _storeSuccess = MutableStateFlow(false)
    val storeSuccess = _storeSuccess.asStateFlow()

    private val _store = MutableStateFlow(emptyList<ResponseGetStore>())
    val store = _store.asStateFlow()

    private val _storeName = MutableStateFlow("")
    val storeName = _storeName.asStateFlow()

    fun searchStore(keyword: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                storeRepository.getStore(keyword)
            }.onSuccess {
                _store.value = it
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun setStoreSuccess() {
        _storeSuccess.value = false
    }

    fun postStore(requestPostStore: RequestPostStore) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                storeRepository.postStore(requestPostStore)
            }.onSuccess {
                updateSelectStoreNum(it)
                _storeSuccess.value = true
                _storeName.value = requestPostStore.name
                updateSelectStore(requestPostStore.name)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    private fun updateSelectStore(storeName: String) {
        viewModelScope.launch(dispatchers.io) {
            addressRepository.updateSelectStore(storeName)
        }
    }

    private fun updateSelectStoreNum(storeNum: Int) {
        viewModelScope.launch(dispatchers.io) {
            addressRepository.updateSelectStoreNum(storeNum)
        }
    }

    fun getSelectStore(): String = runBlocking(dispatchers.io) {
        addressRepository.getSelectStore()
    }

    fun getSelectStoreNum(): Int = runBlocking(dispatchers.io) {
        addressRepository.getSelectStoreNum()
    }

    fun deleteSelectStore() {
        viewModelScope.launch(dispatchers.io) {
            addressRepository.deleteSelectStore()
        }
    }

    fun deleteSelectStoreNum() {
        viewModelScope.launch(dispatchers.io) {
            addressRepository.deleteSelectStoreNum()
        }
    }

    fun post(
        context: Context,
        image: List<Uri?>,
        title: String,
        mini_title: String,
        content: String,
        storeId: Int,
        satisfaction: Int?,
        togetherWith: String?,
        dayTip: String?,
        movingTip: String?,
        orderingTip: String?,
        otherTip: String?,
    ) {
        val imageListMultipartBody = mutableListOf<MultipartBody.Part>()
        val titleRequestBody = title.toPlainRequestBody()
        val miniTitleRequestBody = mini_title.toPlainRequestBody()
        val contentRequestBody = content.toPlainRequestBody()
        val storeIdRequestBody = storeId.toString().toPlainRequestBody()
        val satisfactionRequestBody = satisfaction.toString().toPlainRequestBody()
        val togetherWithRequestBody = togetherWith.toString().toPlainRequestBody()
        val dayTipRequestBody = dayTip.toString().toPlainRequestBody()
        val movingTipRequestBody = movingTip.toString().toPlainRequestBody()
        val orderingTipRequestBody = orderingTip.toString().toPlainRequestBody()
        val otherTipRequestBody = otherTip.toString().toPlainRequestBody()

        for (element in image) {
            val imageMultipartBody: MultipartBody.Part =
                ContentUriRequestBody(context, element ?: continue).toFormData()
            imageListMultipartBody.add(imageMultipartBody)
        }

        viewModelScope.launch(dispatchers.io) {
            runCatching {
                postService.post(
                    imageListMultipartBody,
                    titleRequestBody,
                    miniTitleRequestBody,
                    contentRequestBody,
                    storeIdRequestBody,
                    satisfactionRequestBody,
                    togetherWithRequestBody,
                    dayTipRequestBody,
                    movingTipRequestBody,
                    orderingTipRequestBody,
                    otherTipRequestBody,
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
