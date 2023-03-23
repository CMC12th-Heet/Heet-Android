package org.heet.presentation.forgot.email

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.core.navigation.navscreen.ForgotScreen
import org.heet.data.model.request.RequestPostEmail
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
    val email = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val requestCode = remember { mutableStateOf(false) }
    val verificationTimer = forgotEmailViewModel.timer.collectAsState().value
    val sendEmail = forgotEmailViewModel.sendEmail.collectAsState().value
    val isCorrectCode = forgotEmailViewModel.isCorrectCode.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Back { navController.popBackStack() }
            Title("비밀번호 찾기")
            if (isCorrectCode) {
                Next {
                    forgotEmailViewModel.deleteCode()
                    navController.navigate(ForgotScreen.ForgotPwd.route)
                }
            } else {
                EmptyText()
            }
        }
        Spacer(modifier = Modifier.height(157.dp))
        PretendardDescription("이메일*")
        Spacer(modifier = Modifier.height(13.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlatTextField(
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
            RequestBtn(sendEmail, "인증 요청") {
                if (email.value.trim().isNotEmpty()) {
                    forgotEmailViewModel.postEmail(RequestPostEmail(email = email.value))
                }
            }
        }
        Spacer(modifier = Modifier.height(13.dp))
        Divider(
            Modifier
                .fillMaxWidth()
                .height(3.dp),
            color = White350
        )
        if (sendEmail) {
            Column {
                Spacer(modifier = Modifier.height(36.dp))
                SendCode(
                    verificationCode = code,
                    verificationTimer = verificationTimer,
                    verificationCodeValidState = code.value.trim().isNotEmpty(),
                    requestCode = requestCode,
                    isCorrectCode = isCorrectCode,
                    forgotEmailViewModel = forgotEmailViewModel,
                    keyboardController = keyboardController
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp),
                    color = White350
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Description("인증번호를 받지 못하셨나요?", modifier = Modifier.padding(start = 8.5.dp))
                    Column(
                        modifier = Modifier.width(IntrinsicSize.Max).padding(end = 8.5.dp)
                    ) {
                        ReSendBtn(isCorrectCode) {
                            forgotEmailViewModel.postEmail(
                                RequestPostEmail(
                                    email.value
                                )
                            )
                        }
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SendCode(
    verificationCode: MutableState<String>,
    verificationTimer: String,
    verificationCodeValidState: Boolean,
    requestCode: MutableState<Boolean>,
    isCorrectCode: Boolean,
    forgotEmailViewModel: ForgotEmailViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FlatTextField(
            modifier = Modifier
                .padding(start = 9.dp, end = 117.dp)
                .align(Alignment.CenterStart),
            valueState = verificationCode,
            placeholder = "인증코드 입력",
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
            val text = if (isCorrectCode) "인증 완료" else "인증 요청"
            Text(
                text = verificationTimer,
                modifier = Modifier.padding(end = 21.dp),
                color = Red900,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            RedSmallRoundButton22(
                onClick = {
                    if (!requestCode.value) {
                        forgotEmailViewModel.postCode(verificationCode.value)
                    }
                },
                text = text,
                isCheck = isCorrectCode
            )
        }
    }
}
