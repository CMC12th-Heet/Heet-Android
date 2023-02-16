package org.heet.presentation.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.BigRoundButton
import org.heet.components.RoundInputField
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.Grey500
import org.heet.ui.theme.Red200
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController) {
    val loginIdOrEmail = remember {
        mutableStateOf("")
    }
    val loginPassword = remember {
        mutableStateOf("")
    }
    val isSubscribed = remember {
        mutableStateOf(true)
    }
    val loginIdOrEmailValidState = remember(loginIdOrEmail.value) {
        loginIdOrEmail.value.trim().isNotEmpty()
    }
    val loginPasswordValidState = remember(loginPassword.value) {
        loginIdOrEmail.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_red_text_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 239.dp)
        ) {
            RoundInputField(
                valueState = loginIdOrEmail,
                placeholder = "아이디 또는 이메일 입력",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!loginIdOrEmailValidState) return@KeyboardActions
                    keyboardController?.hide()
                }
            )
            if (!isSubscribed.value) {
                Text(
                    text = "*가입되지 않은 이메일입니다.",
                    modifier = Modifier.padding(top = 6.dp, start = 39.dp, bottom = 14.dp),
                    fontFamily = pretendardFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Red200
                )
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }
            RoundInputField(
                valueState = loginPassword,
                placeholder = "비밀번호 입력",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!loginPasswordValidState) return@KeyboardActions
                    keyboardController?.hide()
                }
            )
            if (!isSubscribed.value) {
                Text(
                    text = "*가입되지 않은 비밀번호입니다.",
                    modifier = Modifier.padding(top = 6.dp, start = 39.dp),
                    fontFamily = pretendardFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Red200
                )
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }
            BigRoundButton(
                onClick = { navController.navigate(HeetScreens.HomeScreen.name) },
                text = "로그인",
                modifier = Modifier.padding(top = 34.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "비밀번호 찾기",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey500,
                    modifier = Modifier.clickable { navController.navigate(HeetScreens.FindPasswordScreen.name) }
                )
                Divider(
                    Modifier
                        .padding(horizontal = 29.dp)
                        .height(16.5.dp)
                        .width(2.dp),
                    color = Red200
                )
                Text(
                    text = "회원가입 하기",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey500,
                    modifier = Modifier.clickable { navController.navigate(HeetScreens.JoinScreen.name) }
                )
            }
        }
    }
}
