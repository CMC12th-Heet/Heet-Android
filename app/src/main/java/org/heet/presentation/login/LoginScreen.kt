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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.*
import org.heet.R
import org.heet.components.BigRoundButton
import org.heet.components.RoundInputField
import org.heet.core.navigation.Graph
import org.heet.data.model.request.RequestLogin
import org.heet.ui.theme.Grey600
import org.heet.ui.theme.Red500
import org.heet.ui.theme.Red600
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val idOrEmail = remember {
        mutableStateOf("")
    }
    val pwd = remember {
        mutableStateOf("")
    }
    val isRegistered = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        loginViewModel.loginSuccess.collect {
            if (it) {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeetLogo()
        Column(
            modifier = Modifier.padding(top = 113.dp)
        ) {
            LoginField(idOrEmail, "아이디 또는 이메일 입력", keyboardController)
            if (!isRegistered.value) {
                RegisterInfo(
                    "*가입되지 않은 이메일입니다.",
                    Modifier.padding(top = 6.dp, start = 19.dp, bottom = 14.dp)
                )
            } else {
                EmptyText(Modifier.padding(top = 20.dp))
            }
            PwdField(pwd, "비밀번호 입력", keyboardController)
            if (!isRegistered.value) {
                RegisterInfo(
                    text = "*가입되지 않은 비밀번호입니다.",
                    modifier = Modifier.padding(top = 6.dp, start = 19.dp, bottom = 34.dp)
                )
            } else {
                EmptyText(Modifier.padding(top = 40.dp))
            }
        }
        BigRoundButton(
            onClick = {
                loginViewModel.login(RequestLogin(idOrEmail.value, pwd.value))
            },
            text = "로그인"
        )
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Auth("비밀번호 찾기") { navController.navigate(Graph.FORGOT) }
            Divider(
                Modifier
                    .padding(horizontal = 29.dp)
                    .height(16.5.dp)
                    .width(2.dp),
                color = Red500
            )
            Auth("회원가입 하기") { navController.navigate(Graph.SIGNUP) }
        }
    }
}

@Composable
private fun HeetLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_text_logo_65),
        contentDescription = "heet_logo",
        modifier = Modifier
            .padding(top = 50.dp)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginField(
    value: MutableState<String>,
    placeholder: String,
    keyboardController: SoftwareKeyboardController?
) {
    RoundInputField(
        valueState = value,
        placeholder = placeholder,
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (value.value.trim().isEmpty()) return@KeyboardActions
            keyboardController?.hide()
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PwdField(
    value: MutableState<String>,
    placeholder: String,
    keyboardController: SoftwareKeyboardController?
) {
    RoundInputField(
        valueState = value,
        placeholder = placeholder,
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (value.value.trim().isEmpty()) return@KeyboardActions
            keyboardController?.hide()
        },
        isPwd = true
    )
}

@Composable
private fun RegisterInfo(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Red600,
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = pretendardFamily
    )
}

@Composable
private fun EmptyText(modifier: Modifier) {
    Text(
        text = "",
        modifier = modifier,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = pretendardFamily
    )
}

@Composable
private fun Auth(text: String, navigate: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier.clickable { navigate() },
        color = Grey600,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    )
}
