package org.heet.presentation.home.mypage.modify

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.core.navigation.navscreen.MyPageScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun ModifyProfileScreen(
    navController: NavController,
    modifyProfileViewModel: ModifyProfileViewModel = hiltViewModel()
) {
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
                Title(text = "????????? ??????")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box {
                Surface(
                    modifier = Modifier.size(71.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, White250)
                ) {
                    Image(
                        painter = painterResource(id = org.heet.R.drawable.img_profile_modify),
                        contentDescription = null
                    )
                }
                Image(
                    painter = painterResource(id = org.heet.R.drawable.ic_gallery_grey_26),
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
                        painter = painterResource(id = org.heet.R.drawable.ic_location_red_15),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "?????? ?????????",
                        color = Red500,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pretendardFamily
                    )
                }
                Text(
                    text = "?????? ????????????",
                    modifier = Modifier.align(Alignment.Bottom),
                    color = Grey50,
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
                    text = "??????",
                    color = Grey50,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "(??????)",
                    color = Black900,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                valueState = name,
                placeholder = "??????",
                enabled = true,
                isSingleLine = true,
                color = Grey150
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "?????????",
                    color = Grey50,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "(??????)",
                    color = Black900,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                valueState = id,
                placeholder = "?????????",
                enabled = true,
                isSingleLine = true,
                color = Grey150
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "??????/??????/???????????? ??? ????????? ??????, 8???~32???",
                modifier = Modifier.align(Alignment.End).padding(13.dp),
                color = White850,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                text = "????????? ??????",
                modifier = Modifier.padding(start = 15.dp).fillMaxWidth(),
                color = Grey50,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(7.dp))
            RoundInputField(
                modifier = Modifier.height(85.dp),
                valueState = introduce,
                placeholder = "?????????",
                enabled = true,
                isSingleLine = true,
                color = Grey150
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "?????? 30 ???",
                modifier = Modifier.align(Alignment.End).padding(end = 13.dp),
                color = Grey50,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "????????????",
                modifier = Modifier.clickable {
                    modifyProfileViewModel.deleteUserPreferences()
                    modifyProfileViewModel.deleteVerify()
                    navController.navigate(MyPageScreen.Login.route)
                },
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.width(65.dp))
            Divider(modifier = Modifier.size(width = 1.dp, height = 18.dp))
            Spacer(modifier = Modifier.width(65.dp))
            Text(
                text = "????????????",
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
