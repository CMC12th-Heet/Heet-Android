package org.heet.presentation.findpassword

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
import androidx.compose.ui.graphics.Color
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
import org.heet.components.FlatInputField
import org.heet.components.SmallRoundButton
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordScreen(
    navController: NavController,
    findPasswordViewModel: FindPasswordViewModel = hiltViewModel()
) {
    val findEmail = remember {
        mutableStateOf("")
    }
    val findEmailValidState = remember(findEmail.value) {
        findEmail.value.trim().isNotEmpty()
    }
    val verificationCode = remember {
        mutableStateOf("")
    }
    val verificationCodeValidState = remember(verificationCode.value) {
        verificationCode.value.trim().isNotEmpty()
    }
    val verificationTimer = remember {
        findPasswordViewModel.timer
    }
    val requestVerification = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 14.dp)
        ) {
            FindPasswordTitle(navController)
            Column(
                modifier = Modifier
                    .padding(top = 153.dp)
            ) {
                InputEmailField(
                    findEmail,
                    findPasswordViewModel,
                    findEmailValidState,
                    requestVerification,
                    keyboardController
                )
                if (requestVerification.value) {
                    InputVerificationCode(
                        verificationCode,
                        verificationTimer,
                        verificationCodeValidState,
                        keyboardController
                    )
                    ReVerificationCode(findPasswordViewModel)
                    Next(navController, Modifier.align(Alignment.End), findPasswordViewModel)
                }
            }
        }
    }
}

@Composable
private fun Next(navController: NavController, modifier: Modifier, findPasswordViewModel: FindPasswordViewModel) {
    Row(
        modifier = modifier
            .padding(top = 98.dp)
            .clickable {
                findPasswordViewModel.timerReset()
                navController.navigate(HeetScreens.ResetPasswordScreen.name)
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "다음",
            modifier = Modifier.padding(end = 5.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = Red400
        )
        Image(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = null
        )
    }
}

@Composable
private fun ReVerificationCode(findPasswordViewModel: FindPasswordViewModel) {
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
            color = Grey200,
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
                findPasswordViewModel.timerStart()
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputVerificationCode(
    verificationCode: MutableState<String>,
    verificationTimer: StateFlow<String>,
    verificationCodeValidState: Boolean,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
    ) {
        FlatInputField(
            modifier = Modifier
                .padding(end = 117.dp)
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
                text = "인증 요청"
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputEmailField(
    findEmail: MutableState<String>,
    findPasswordViewModel: FindPasswordViewModel,
    findEmailValidState: Boolean,
    requestVerification: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?
) {
    Text(
        text = "이메일*",
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Red200
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        FlatInputField(
            modifier = Modifier
                .padding(end = 117.dp)
                .align(Alignment.CenterStart),
            valueState = findEmail,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!findEmailValidState) return@KeyboardActions
                keyboardController?.hide()
            }
        )
        SmallRoundButton(
            onClick = {
                findPasswordViewModel.timerStart()
                requestVerification.value = true
            },
            modifier = Modifier.align(Alignment.CenterEnd),
            text = "인증 요청"
        )
    }
    Divider(
        Modifier
            .padding(top = 13.dp)
            .fillMaxWidth()
            .height(3.dp),
        color = Red200
    )
}

@Composable
private fun FindPasswordTitle(navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Text(
            text = "비밀번호 찾기",
            fontFamily = pretendardFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(start = 105.dp)
        )
    }
}
