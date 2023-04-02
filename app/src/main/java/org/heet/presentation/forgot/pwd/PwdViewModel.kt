package org.heet.presentation.forgot.pwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestResetPwd
import org.heet.data.provider.DispatcherProvider
import org.heet.domain.repository.ResetRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PwdViewModel @Inject constructor(
    private val resetRepository: ResetRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _resetSuccess = MutableStateFlow(false)
    val resetSuccess = _resetSuccess.asStateFlow()

    fun resetEmail(password: String, email: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                resetRepository.resetPwd(RequestResetPwd(password, email))
            }.onSuccess {
                _resetSuccess.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
