package org.heet.presentation.signup.welcome

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
import org.heet.components.RedBigRoundButton28
import org.heet.core.navigation.navscreen.AuthScreen
import org.heet.core.navigation.navscreen.SignUpScreen
import org.heet.ui.theme.Grey650
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White250
import org.heet.ui.theme.White600
import org.heet.util.pretendardFamily

@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
            Spacer(modifier = Modifier.height(182.dp))
            Greeting()
            Spacer(modifier = Modifier.height(32.dp))
            GreetingUser(welcomeViewModel)
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (welcomeViewModel.getResidence() == "") {
                RedBigRoundButton28(text = "동네 설정하기") {
                    navController.navigate(SignUpScreen.Residence.route)
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_location_red_28),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = welcomeViewModel.getResidence(),
                    fontFamily = pretendardFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Red500,
                )
                Spacer(modifier = Modifier.height(18.dp))
                RedBigRoundButton28(text = "시작하기") {
                    navController.navigate(AuthScreen.Login.route) {
                        welcomeViewModel.signUp()
                        if (welcomeViewModel.signUp.value) {
                            popUpTo(0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_text_logo_red),
        contentDescription = null,
    )
}

@Composable
private fun Greeting() {
    Text(
        text = "안녕하세요",
        color = Red500,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = pretendardFamily,
    )
}

@Composable
private fun GreetingUser(welcomeViewModel: WelcomeViewModel) {
    Row {
        Text(
            text = welcomeViewModel.getUsername(),
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = White600,
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "님",
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Grey650,
        )
    }
    Spacer(modifier = Modifier.width(2.dp))
    Divider(color = White250, modifier = Modifier.width(159.dp))
}
