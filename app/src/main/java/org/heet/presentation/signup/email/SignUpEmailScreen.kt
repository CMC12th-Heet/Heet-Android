package org.heet.presentation.signup.email

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.StateFlow
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.SignUpScreen
import org.heet.data.model.request.RequestPostEmail
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpEmailScreen(
    navController: NavController,
    signUpEmailViewModel: SignUpEmailViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val email = remember {
        mutableStateOf("")
    }
    val code = remember {
        mutableStateOf("")
    }
    val requestCode = remember {
        mutableStateOf(false)
    }
    val sendEmail = signUpEmailViewModel.sendEmail.collectAsState().value
    val verificationTimer = remember {
        signUpEmailViewModel.timer
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title("????????????")
                if (requestCode.value) {
                    Next(
                        delete = { signUpEmailViewModel.deleteCode() },
                        timer = { signUpEmailViewModel.timerReset() },
                        move = { navController.navigate(SignUpScreen.SignUpPwd.route) }
                    )
                } else {
                    EmptyText()
                }
            }
            Spacer(modifier = Modifier.height(137.dp))
            EmailField(
                email = email,
                placeholder = "????????? ??????",
                keyboardController = keyboardController
            )
            BigRoundButton(
                onClick = {
                    if (email.value.trim().isNotEmpty()) {
                        signUpEmailViewModel.updateEmail(email.value)
                        signUpEmailViewModel.postEmail(RequestPostEmail(email = email.value))
                    }
                },
                text = "????????? ????????????",
                modifier = Modifier.padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (sendEmail) {
                Column {
                    Row(modifier = Modifier.padding(start = 6.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_check_black_20),
                            contentDescription = "black_check"
                        )
                        Spacer(modifier = Modifier.width(9.dp))
                        Text(
                            text = "????????? ????????? ?????????????????????.\n?????? ?????? 6????????? ??????????????????.",
                            fontFamily = pretendardFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = White500
                        )
                    }
                    SendCode(
                        verificationCode = code,
                        verificationTimer = verificationTimer,
                        verificationCodeValidState = code.value.trim()
                            .isNotEmpty(),
                        signUpEmailViewModel = signUpEmailViewModel,
                        requestCode = requestCode,
                        keyboardController = keyboardController
                    )
                    Divider(
                        Modifier
                            .padding(top = 13.dp)
                            .fillMaxWidth()
                            .height(2.dp),
                        color = White350
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Description("??????????????? ?????? ????????????????", modifier = Modifier.padding(start = 8.5.dp))
                        Column(
                            modifier = Modifier.width(IntrinsicSize.Max).padding(end = 8.5.dp)
                        ) {
                            ReSendBtn(requestCode) { signUpEmailViewModel.timerStart() }
                            Divider(
                                Modifier
                                    .padding(top = 2.dp)
                                    .height(1.dp),
                                color = Grey700
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SendCode(
    verificationCode: MutableState<String>,
    verificationTimer: StateFlow<String>,
    verificationCodeValidState: Boolean,
    requestCode: MutableState<Boolean>,
    signUpEmailViewModel: SignUpEmailViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
    ) {
        FlatInputField(
            modifier = Modifier
                .padding(start = 9.dp, end = 117.dp)
                .align(Alignment.CenterStart),
            valueState = verificationCode,
            placeholder = "???????????? ??????",
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!verificationCodeValidState) return@KeyboardActions
                keyboardController?.hide()
            }
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val text = if (requestCode.value) "?????? ??????" else "?????? ??????"
            Text(
                text = verificationTimer.collectAsState().value,
                fontFamily = pretendardFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Red900,
                modifier = Modifier.padding(end = 21.dp)
            )
            SmallRoundButton(
                onClick = {
                    if (!requestCode.value) {
                        if (verificationCode.value == signUpEmailViewModel.code.value.toString()) {
                            signUpEmailViewModel.timerReset()
                            requestCode.value = true
                        }
                    }
                },
                text = text,
                isCheck = requestCode
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EmailField(
    email: MutableState<String>,
    placeholder: String,
    keyboardController: SoftwareKeyboardController?
) {
    RoundInputField(
        valueState = email,
        placeholder = placeholder,
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (email.value.trim().isEmpty()) return@KeyboardActions
            keyboardController?.hide()
        }
    )
}
