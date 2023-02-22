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
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.BigRoundButton
import org.heet.components.RoundInputField
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.Grey600
import org.heet.ui.theme.Red200
import org.heet.ui.theme.Red400
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController) {
    val loginStateHolder = remember { LoginStateHolder() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeetLogo()
        Column(
            modifier = Modifier.padding(top = 113.dp)
        ) {
            LoginField(loginStateHolder.idOrEmail, "아이디 또는 이메일 입력", keyboardController)
            if (!loginStateHolder.isRegistered.value) {
                RegisterInfo(
                    "*가입되지 않은 이메일입니다.",
                    Modifier.padding(top = 6.dp, start = 19.dp, bottom = 14.dp)
                )
            } else {
                EmptyText(Modifier.padding(top = 20.dp))
            }
            LoginField(loginStateHolder.pwd, "비밀번호 입력", keyboardController)
            if (!loginStateHolder.isRegistered.value) {
                RegisterInfo(
                    text = "*가입되지 않은 비밀번호입니다.",
                    modifier = Modifier.padding(top = 6.dp, start = 19.dp, bottom = 34.dp)
                )
            } else {
                EmptyText(Modifier.padding(top = 40.dp))
            }
        }
        LoginButton(navController)
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Auth("비밀번호 찾기") { navController.navigate(HeetScreens.FindPwdScreen.name) }
            Divider(
                Modifier
                    .padding(horizontal = 29.dp)
                    .height(16.5.dp)
                    .width(2.dp),
                color = Red400
            )
            Auth("회원가입 하기") { navController.navigate(HeetScreens.JoinEmailScreen.name) }
        }
    }
}

@Composable
private fun HeetLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_red_text_logo),
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

@Composable
private fun RegisterInfo(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Red200,
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
private fun LoginButton(navController: NavController) {
    BigRoundButton(
        onClick = { navController.navigate(HeetScreens.HomeScreen.name) },
        text = "로그인"
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
