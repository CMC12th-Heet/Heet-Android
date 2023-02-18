package org.heet.presentation.join

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
import org.heet.ui.theme.Grey900
import org.heet.ui.theme.Red400
import org.heet.util.pretendardFamily

@Composable
fun JoinFinish(navController: NavController) {
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
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
        ) {
            NeighborhoodButton() {
                navController.navigate(HeetScreens.NeighborhoodSettingScreen.name)
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
            color = Grey900
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
private fun NeighborhoodButton(onClick: () -> Unit) {
    BigRoundButton(
        onClick = onClick,
        text = "동네 설정하기"
    )
}
