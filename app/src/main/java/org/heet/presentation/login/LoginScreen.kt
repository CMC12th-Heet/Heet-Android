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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.*
import org.heet.R
import org.heet.components.GreyRoundTextField23
import org.heet.components.RedBigRoundButton28
import org.heet.core.navigation.Graph
import org.heet.data.model.request.RequestLogin
import org.heet.ui.theme.Grey600
import org.heet.ui.theme.Red500
import org.heet.ui.theme.Red600
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val (focusRequester) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current
    val idOrEmail = remember { mutableStateOf("") }
    val pwd = remember { mutableStateOf("") }
    val isRegistered = loginViewModel.registered.collectAsState().value

    LaunchedEffect(loginViewModel.loginSuccess.collectAsState().value) {
        if (loginViewModel.loginSuccess.value) {
            navController.popBackStack()
            navController.navigate(Graph.HOME)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Logo()
        Spacer(modifier = Modifier.height(113.dp))
        GreyRoundTextField23(
            value = idOrEmail,
            placeholder = "아이디 또는 이메일 입력",
            onAction = KeyboardActions(
                onNext = { focusRequester.requestFocus() },
            ),
        ) {
            loginViewModel.setRegisterTrue()
        }
        RegisterDescription(
            modifier = Modifier.align(Alignment.Start),
            isRegistered = isRegistered,
            description = "*가입되지 않은 이메일입니다.",
        )
        GreyRoundTextField23(
            modifier = Modifier.focusRequester(focusRequester),
            value = pwd,
            placeholder = "비밀번호 입력",
            onAction = KeyboardActions {
                if (idOrEmail.value.trim().isEmpty()) return@KeyboardActions
                keyboardController?.hide()
            },
            isPwd = true,
            imeAction = ImeAction.Done,
        ) {
            loginViewModel.setRegisterTrue()
        }
        RegisterDescription(
            modifier = Modifier.align(Alignment.Start),
            isRegistered = isRegistered,
            description = "*가입되지 않은 비밀번호입니다.",
            dp = 20.dp,
        )
        RedBigRoundButton28(
            onClick = { loginViewModel.login(RequestLogin(idOrEmail.value, pwd.value)) },
            text = "로그인",
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Auth("비밀번호 찾기") { navController.navigate(Graph.FORGOT) }
            Divider(
                Modifier
                    .padding(horizontal = 29.dp)
                    .height(16.5.dp)
                    .width(2.dp),
                color = Red500,
            )
            Auth("회원가입 하기") {
                loginViewModel.deleteAll()
                navController.navigate(Graph.SIGNUP)
            }
        }
    }
}

@Composable
private fun RegisterDescription(
    modifier: Modifier = Modifier,
    isRegistered: Boolean,
    description: String,
    dp: Dp = 0.dp,
) {
    if (!isRegistered) {
        RegisterInfo(
            text = description,
            modifier = modifier.padding(top = 6.dp, start = 19.dp, bottom = 14.dp + dp),
        )
    } else {
        EmptyText(Modifier.padding(top = 20.dp + dp))
    }
}

@Composable
private fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_text_logo_65),
        contentDescription = "heet_logo",
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
        fontFamily = pretendardFamily,
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
        fontFamily = pretendardFamily,
    )
}

@Composable
private fun Auth(purpose: String, navigate: () -> Unit) {
    Text(
        text = purpose,
        modifier = Modifier.clickable { navigate() },
        color = Grey600,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
    )
}
