package org.heet.presentation.signup.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.data.model.request.RequestPostEmail
import org.heet.domain.repository.SignUpRepository
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpEmailViewModel @Inject constructor(private val signUpRepository: SignUpRepository) :
    ViewModel() {

    private var timerCount = 300000
    private val _timer = MutableStateFlow("05:00")
    val timer = _timer.asStateFlow()

    private val _sendEmail = MutableStateFlow(false)
    val sendEmail = _sendEmail.asStateFlow()

    private lateinit var job: Job

    fun postEmail(requestPostEmail: RequestPostEmail) {
        viewModelScope.launch {
            runCatching {
                signUpRepository.postEmail(requestPostEmail)
            }.onSuccess {
                _sendEmail.value = true
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }

    fun timerStart() {
        if (::job.isInitialized) job.cancel()
        _timer.value = "05:00"
        timerCount = 300000
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        job = viewModelScope.launch {
            while (timerCount > 0) {
                delay(1000L)
                timerCount -= 1000
                _timer.value = dateFormat.format(Date(timerCount.toLong()))
            }
        }
    }

    fun timerReset() {
        _timer.value = "05:00"
        job.cancel()
    }
}
