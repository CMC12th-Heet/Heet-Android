package org.heet.presentation.neighborhood

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
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.BigRoundButton
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.Red400
import org.heet.ui.theme.White900
import org.heet.util.pretendardFamily

@Composable
fun SettingFinishScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
            Spacer(modifier = Modifier.height(182.dp))
            Greeting()
            Spacer(modifier = Modifier.height(42.dp))
            GreetingUser()
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.ic_location), contentDescription = null)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "서울시 용산구",
                fontFamily = pretendardFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Red400
            )
            Spacer(modifier = Modifier.height(18.dp))
            StartButton() {
                navController.navigate(HeetScreens.LoginScreen.name) {
                    popUpTo(0)
                }
            }
        }
    }
}

@Composable
private fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_red_text_logo),
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
        color = Red400
    )
}

@Composable
private fun GreetingUser() {
    Row {
        Text(
            text = "kimjaehee",
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = White900
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "님",
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Red400
        )
    }
    Spacer(modifier = Modifier.width(2.dp))
    Divider(color = Red400, modifier = Modifier.width(159.dp))
}

@Composable
private fun StartButton(onClick: () -> Unit) {
    BigRoundButton(
        onClick = onClick,
        text = "시작하기"
    )
}
