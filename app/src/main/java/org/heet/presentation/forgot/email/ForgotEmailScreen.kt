package org.heet.presentation.forgot

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.core.navigation.navscreen.ForgotScreen
import org.heet.presentation.forgot.email.ForgotEmailViewModel
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotEmailScreen(
    navController: NavController,
    forgotEmailViewModel: ForgotEmailViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val email = remember {
        mutableStateOf("")
    }
    val code = remember {
        mutableStateOf("")
    }
    val requestEmail = remember {
        mutableStateOf(false)
    }
    val requestCode = remember {
        mutableStateOf(false)
    }
    val verificationTimer = remember {
        forgotEmailViewModel.timer
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
                Title("비밀번호 찾기")
                if (requestCode.value) {
                    Next(
                        timer = { forgotEmailViewModel.timerReset() },
                        move = { navController.navigate(ForgotScreen.ForgotPwd.route) }
                    )
                } else {
                    EmptyText()
                }
            }
            Column(modifier = Modifier.padding(top = 153.dp)) {
                PretendardDescription("이메일*")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FlatInputField(
                        modifier = Modifier.width(203.dp),
                        valueState = email,
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Email,
                        onAction = KeyboardActions {
                            if (email.value.trim().isEmpty()) return@KeyboardActions
                            keyboardController?.hide()
                        }
                    )
                    RequestBtn(requestEmail, "인증 요청") {
                        requestEmail.value = true
                    }
                }
                Divider(
                    Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = White350
                )
                if (requestEmail.value) {
                    Column(
                        modifier = Modifier
                            .padding(top = 36.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            FlatInputField(
                                modifier = Modifier
                                    .padding(end = 182.dp)
                                    .align(Alignment.CenterStart),
                                valueState = code,
                                placeholder = "인증코드 입력",
                                enabled = true,
                                isSingleLine = true,
                                keyboardType = KeyboardType.Email,
                                onAction = KeyboardActions {
                                    if (code.value.trim()
                                        .isEmpty()
                                    ) return@KeyboardActions
                                    keyboardController?.hide()
                                }
                            )
                            Row(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = verificationTimer.collectAsState().value,
                                    modifier = Modifier.padding(end = 21.dp),
                                    color = Red900,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = pretendardFamily
                                )
                                RequestBtn(isCheck = requestCode, "인증 요청") {
                                    if (!requestCode.value) {
                                        forgotEmailViewModel.timerStart()
                                    }
                                    requestCode.value = true
                                }
                            }
                        }
                        Divider(
                            Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .height(2.dp),
                            color = White150
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Description("인증번호를 받지 못하셨나요?", modifier = Modifier.padding(start = 8.5.dp))
                        Column(
                            modifier = Modifier.width(IntrinsicSize.Max).padding(end = 8.5.dp)
                        ) {
                            ReSendBtn(requestCode) { forgotEmailViewModel.timerStart() }
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
