package org.heet.presentation.signup.id

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.heet.domain.repository.StoreEmailPwdRepository
import javax.inject.Inject

@HiltViewModel
class SingUpIdViewModel @Inject constructor(private val storeEmailPwdRepository: StoreEmailPwdRepository) :
    ViewModel() {

    private val _pwd = MutableStateFlow("")
    val pwd = _pwd.asStateFlow()

    init {
        viewModelScope.launch {
            storeEmailPwdRepository.getEmail().collect() { pwd ->
                repeat(pwd.length) {
                    _pwd.value += "*"
                }
            }
        }
    }
}
