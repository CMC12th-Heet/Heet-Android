package org.heet.presentation.join

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.StateFlow
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JoinCertificationScreen(
    navController: NavController,
    joinCertificationViewModel: JoinCertificationViewModel = hiltViewModel()
) {
    val email = remember {
        mutableStateOf("")
    }
    val sendCertification = remember {
        mutableStateOf(false)
    }
    val verificationCode = remember {
        mutableStateOf("")
    }
    val isRequestedCode = remember {
        mutableStateOf(false)
    }
    val verificationCodeValidState = remember(verificationCode.value) {
        verificationCode.value.trim().isNotEmpty()
    }
    val verificationTimer = remember {
        joinCertificationViewModel.timer
    }
    val emailValidState = remember(email.value) {
        email.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            ScreenTitle(
                title = "회원 가입",
                modifier = Modifier.padding(start = 118.dp)
            ) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(137.dp))
            RoundInputField(
                valueState = email,
                placeholder = "이메일 입력",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!emailValidState) return@KeyboardActions
                    keyboardController?.hide()
                }
            )
            BigRoundButton(
                onClick = {
                    sendCertification.value = true
                    joinCertificationViewModel.timerStart()
                },
                text = "이메일 인증하기",
                modifier = Modifier.padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (sendCertification.value) {
                Column {
                    Row(modifier = Modifier.padding(start = 6.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_green_check),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(9.dp))
                        Text(
                            text = "이메일 전송이 성공되엇습니다.\n인증 코드 6자리를 입력해주세요.",
                            fontFamily = pretendardFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = White800
                        )
                    }
                    InputVerificationCode(
                        verificationCode = verificationCode,
                        verificationTimer = verificationTimer,
                        verificationCodeValidState = verificationCodeValidState,
                        isRequestCode = isRequestedCode,
                        keyboardController = keyboardController
                    )
                    ReVerificationCode(
                        joinCertificationViewModel = joinCertificationViewModel
                    )
                    NextImage(
                        navController,
                        Modifier.align(Alignment.End),
                        HeetScreens.JoinEmailPwdScreen.name
                    ) {
                        joinCertificationViewModel.timerReset()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputVerificationCode(
    verificationCode: MutableState<String>,
    verificationTimer: StateFlow<String>,
    verificationCodeValidState: Boolean,
    isRequestCode: MutableState<Boolean>,
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
            Text(
                text = verificationTimer.collectAsState().value,
                fontFamily = pretendardFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Red500,
                modifier = Modifier.padding(end = 21.dp)
            )
            SmallRoundButton(
                onClick = { },
                text = "인증 요청",
                isCheck = isRequestCode
            )
        }
    }
    Divider(
        Modifier
            .padding(top = 13.dp)
            .fillMaxWidth()
            .height(2.dp),
        color = Red100
    )
}

@Composable
private fun ReVerificationCode(joinCertificationViewModel: JoinCertificationViewModel) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "인증번호를 받지 못하셨나요?",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Grey300,
            modifier = Modifier.padding(start = 8.5.dp)
        )
        Text(
            text = "재전송하기",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Grey700,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(end = 8.5.dp).clickable {
                joinCertificationViewModel.timerStart()
            }
        )
    }
}
