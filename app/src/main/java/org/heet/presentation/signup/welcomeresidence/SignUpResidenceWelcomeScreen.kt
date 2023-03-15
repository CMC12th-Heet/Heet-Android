package org.heet.presentation.signup.welcomeresidence

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.RedRoundButton28
import org.heet.core.navigation.navscreen.AuthScreen
import org.heet.ui.theme.Grey650
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White250
import org.heet.ui.theme.White600
import org.heet.util.pretendardFamily

@Composable
fun SignUpResidenceWelcomeScreen(
    navController: NavController,
    signUpResidenceWelcomeViewModel: SignUpResidenceWelcomeViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
            Spacer(modifier = Modifier.height(182.dp))
            Greeting()
            Spacer(modifier = Modifier.height(32.dp))
            GreetingUser(signUpResidenceWelcomeViewModel.getUsername())
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_red_28),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = signUpResidenceWelcomeViewModel.getResidence(),
                fontFamily = pretendardFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Red500
            )
            Spacer(modifier = Modifier.height(18.dp))
            StartButton() {
                navController.navigate(AuthScreen.Login.route) {
                    signUpResidenceWelcomeViewModel.signUp()
                    if (signUpResidenceWelcomeViewModel.signUp.value) {
                        popUpTo(0)
                    }
                }
            }
        }
    }
}

@Composable
private fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_text_logo_65),
        contentDescription = null
    )
}

@Composable
private fun Greeting() {
    Text(
        text = "안녕하세요",
        fontFamily = pretendardFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Red500
    )
}

@Composable
private fun GreetingUser(username: String) {
    Row {
        Text(
            text = username,
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = White600
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "님",
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Grey650
        )
    }
    Spacer(modifier = Modifier.width(2.dp))
    Divider(color = White250, modifier = Modifier.width(159.dp))
}

@Composable
private fun StartButton(onClick: () -> Unit) {
    RedRoundButton28(
        onClick = onClick,
        text = "시작하기"
    )
}
