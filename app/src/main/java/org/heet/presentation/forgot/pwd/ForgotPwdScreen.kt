package org.heet.presentation.forgot.pwd

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.AuthScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPwdScreen(
    navController: NavController,
    email: String,
    pwdViewModel: PwdViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pwd = remember { mutableStateOf("") }
    val secondPwd = remember { mutableStateOf("") }
    val isNumber = remember { mutableStateOf(false) }
    val isAlphabet = remember { mutableStateOf(false) }
    val isSpecialChar = remember { mutableStateOf(false) }
    val isValidateLength = remember { mutableStateOf(false) }
    val isValidatePwd = remember { mutableStateOf(false) }
    val checkPwd = remember { mutableStateOf(false) }
    val isHide = remember { mutableStateOf(true) }
    val isSame = remember { mutableStateOf(false) }

    LaunchedEffect(pwdViewModel.resetSuccess.collectAsState().value) {
        if (pwdViewModel.resetSuccess.value) {
            navController.navigate(AuthScreen.Login.route) {
                popUpTo(0)
            }
        }
    }

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
            Title("비밀번호 재설정")
            if (isSame.value) {
                Finish(
                    modifier = Modifier.clickable {
                        pwdViewModel.resetEmail(pwd.value, email)
                    }
                )
            } else {
                EmptyText()
            }
        }
        Spacer(modifier = Modifier.height(158.dp))
        PretendardDescription("새 비밀번호를 입력하세요*")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PwdField(
                pwd = pwd,
                pwdValidState = pwd.value.trim().isNotEmpty(),
                isHide = isHide,
                keyboardController = keyboardController,
                isNumber = isNumber,
                isAlphabet = isAlphabet,
                isSpecialChar = isSpecialChar,
                isValidateLength = isValidateLength,
                isValidatePwd = isValidatePwd,
                checkPwd = checkPwd
            )
            Hide(isHide = isHide.value) {
                isHide.value = !isHide.value
            }
        }
        Divider(
            Modifier
                .fillMaxWidth()
                .height(3.dp),
            color = White350
        )
        if (!checkPwd.value) {
            if (pwd.value.isNotEmpty()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    ValidateText("숫자", isNumber.value)
                    ValidateText("영문", isAlphabet.value)
                    ValidateText("특수문자", isSpecialChar.value)
                    ValidateText("8자 이상", isValidateLength.value)
                }
            }
        } else {
            Spacer(modifier = Modifier.height(18.dp))
            Column {
                PretendardDescription("비밀번호 재확인*")
                SecondPwdField(
                    pwd = pwd,
                    secondPwd = secondPwd,
                    secondPwdValidState = secondPwd.value.trim()
                        .isNotEmpty(),
                    isHide = isHide,
                    isSame = isSame,
                    keyboardController = keyboardController
                )
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(3.dp),
                    color = White350
                )
                if (isSame.value) {
                    Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_check_black_20),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 7.dp)
                        )
                        Text(
                            text = "일치합니다.",
                            color = White750,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                }
            }
        }
    }
}
