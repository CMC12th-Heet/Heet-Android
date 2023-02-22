package org.heet.presentation.findpwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FindPwdViewModel : ViewModel() {

    private var timerCount = 300000
    private val _timer = MutableStateFlow("05:00")
    val timer = _timer.asStateFlow()

    private lateinit var job: Job

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
