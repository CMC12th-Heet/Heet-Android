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
    signUpEmailViewModel: SignUpEmailViewModel = hiltViewModel(),
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val email = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val requestCode = remember { mutableStateOf(false) }
    val verificationTimer = signUpEmailViewModel.timer.collectAsState().value
    val sendEmail = signUpEmailViewModel.sendEmail.collectAsState().value
    val isCorrectCode = signUpEmailViewModel.isCorrectCode.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Back { navController.popBackStack() }
            Title("회원가입")
            if (isCorrectCode) {
                Next {
                    signUpEmailViewModel.deleteCode()
                    navController.navigate(SignUpScreen.SignUpPwd.route)
                }
            } else {
                EmptyText()
            }
        }
        Spacer(modifier = Modifier.height(141.dp))
        GreyRoundTextField23(
            value = email,
            placeholder = "이메일 입력",
            onAction = KeyboardActions {
                if (email.value.trim().isEmpty()) return@KeyboardActions
                keyboardController?.hide()
            },
        )
        Spacer(modifier = Modifier.height(15.dp))
        RedBigRoundButton28(
            onClick = {
                if (email.value.trim().isNotEmpty()) {
                    signUpEmailViewModel.updateEmail(email.value)
                    signUpEmailViewModel.postEmail(RequestPostEmail(email = email.value))
                }
            },
            text = "이메일 인증하기",
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (sendEmail) {
            Column {
                NoticeSendEmailSuccess()
                Spacer(modifier = Modifier.height(20.dp))
                SendCode(
                    verificationCode = code,
                    verificationTimer = verificationTimer,
                    verificationCodeValidState = code.value.trim().isNotEmpty(),
                    signUpEmailViewModel = signUpEmailViewModel,
                    requestCode = requestCode,
                    isCorrectCode = isCorrectCode,
                    keyboardController = keyboardController,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp),
                    color = White350,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Description("인증번호를 받지 못하셨나요?", modifier = Modifier.padding(start = 8.5.dp))
                    Column(
                        modifier = Modifier.width(IntrinsicSize.Max).padding(end = 8.5.dp),
                    ) {
                        ReSendBtn(isCorrectCode) {
                            signUpEmailViewModel.postEmail(
                                RequestPostEmail(
                                    email.value,
                                ),
                            )
                        }
                        Divider(
                            Modifier
                                .padding(top = 2.dp)
                                .height(1.dp),
                            color = Grey700,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NoticeSendEmailSuccess() {
    Row(modifier = Modifier.padding(start = 6.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_check_20),
            contentDescription = "black_check",
        )
        Spacer(modifier = Modifier.width(9.dp))
        Text(
            text = "이메일 전송이 성공되엇습니다.\n인증 코드 6자리를 입력해주세요.",
            color = White500,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily,
        )
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
    signUpEmailViewModel: SignUpEmailViewModel,
    keyboardController: SoftwareKeyboardController?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
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
            },
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val text = if (isCorrectCode) "인증 완료" else "인증 요청"
            Text(
                text = verificationTimer,
                modifier = Modifier.padding(end = 21.dp),
                color = Red900,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily,
            )
            RedSmallRoundButton22(
                onClick = {
                    if (!requestCode.value) {
                        signUpEmailViewModel.postCode(verificationCode.value)
                    }
                },
                text = text,
                isCheck = isCorrectCode,
            )
        }
    }
}
