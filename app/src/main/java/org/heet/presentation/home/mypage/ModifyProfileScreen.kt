package org.heet.presentation.home.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun ModifyProfileScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val id = remember { mutableStateOf("") }
    val introduce = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        Withdraw(showDialog)
    }

    Column(
        modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Back { navController.popBackStack() }
                Title(text = "프로필 수정")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box {
                Surface(
                    modifier = Modifier.size(71.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, White300)
                ) {
                    Image(
                        painter = painterResource(id = org.heet.R.drawable.img_modify_profile_default_profile),
                        contentDescription = null
                    )
                }
                Image(
                    painter = painterResource(id = org.heet.R.drawable.ic_gallery),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = org.heet.R.drawable.ic_mypage_profile_location),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "중구 약수동",
                        color = Red200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pretendardFamily
                    )
                }
                Text(
                    text = "지역 변경하기",
                    modifier = Modifier.align(Alignment.Bottom),
                    color = Grey1400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "이름",
                    color = Grey1400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "(필수)",
                    color = Black1500,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                valueState = name,
                placeholder = "이름",
                enabled = true,
                isSingleLine = true,
                color = Black1600
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "아이디",
                    color = Grey1400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "(필수)",
                    color = Black1500,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                valueState = id,
                placeholder = "아이디",
                enabled = true,
                isSingleLine = true,
                color = Black1600
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "숫자/영문/특수문자 중 두가지 이상, 8지~32자",
                modifier = Modifier.align(Alignment.End).padding(13.dp),
                color = Grey1500,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                text = "소개글 작성",
                modifier = Modifier.padding(start = 15.dp).fillMaxWidth(),
                color = Grey1400,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                modifier = Modifier.height(85.dp),
                valueState = introduce,
                placeholder = "소개글",
                enabled = true,
                isSingleLine = true,
                color = Black1600
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "최대 30 자",
                modifier = Modifier.align(Alignment.End).padding(end = 13.dp),
                color = Grey1400,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "로그아웃",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.width(65.dp))
            Divider(modifier = Modifier.size(width = 1.dp, height = 18.dp))
            Spacer(modifier = Modifier.width(65.dp))
            Text(
                text = "회원탈퇴",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily,
                modifier = Modifier.clickable {
                    showDialog.value = true
                }
            )
        }
        Spacer(modifier = Modifier.height(38.dp))
    }
}

@Composable
fun Withdraw(showDialog: MutableState<Boolean>) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            WithdrawDialog(showDialog = showDialog)
        }
    }
}
