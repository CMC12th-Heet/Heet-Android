package org.heet.presentation.home.hometown.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.ui.theme.Black400
import org.heet.ui.theme.Grey100
import org.heet.ui.theme.White700
import org.heet.util.pretendardFamily

@Composable
fun PostScreen(navController: NavController, postViewModel: PostViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val title = remember { mutableStateOf("") }
    val subTitle = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val expandSatisfaction = remember { mutableStateOf(false) }
    val expandShare = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 14.dp, end = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cancel_black_30),
                contentDescription = "cancel",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    postViewModel.deleteSelectStore()
                }
            )
            Title(text = "우리동네 기록")
            Finish {
                navController.popBackStack()
                postViewModel.deleteSelectStore()
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            shape = RoundedCornerShape(30.dp),
            color = White700,
            modifier = Modifier.clickable {
                navController.navigate(HomeTownScreen.Address.route)
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (postViewModel.getSelectStore() == "") "주소를 등록해주세요" else postViewModel.getSelectStore(),
                    modifier = Modifier.padding(start = 20.dp, top = 7.dp, bottom = 7.dp),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = pretendardFamily
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_white_search_14),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(17.dp))
            }
        }
        Spacer(modifier = Modifier.height(11.dp))
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(shape = RoundedCornerShape(5.dp)) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img_default_post),
                        contentDescription = "image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 12.dp, bottom = 11.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(
                            text = "1/7",
                            modifier = Modifier.padding(horizontal = 13.dp),
                            color = Black400,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = pretendardFamily
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            NormalInputField(
                valueState = title,
                placeholder = "제목",
                enabled = true,
                isSingleLine = true
            )
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            NormalInputField(
                valueState = subTitle,
                placeholder = "소제목",
                enabled = true,
                isSingleLine = true,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            NormalInputField(
                modifier = Modifier.height(73.dp),
                valueState = content,
                placeholder = "내용을 입력하세요.",
                enabled = true,
                isSingleLine = false,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(17.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(13.dp))
            Surface(
                shape = RoundedCornerShape(30.dp),
                color = White700
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "꿀팁 정보들 공유하기",
                        modifier = Modifier.padding(start = 20.dp, top = 7.dp, bottom = 7.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pretendardFamily
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Image(
                        painter = if (expandShare.value) {
                            painterResource(id = R.drawable.ic_white_down_16)
                        } else {
                            painterResource(id = R.drawable.ic_next_white_16)
                        },
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            expandShare.value = !expandShare.value
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Surface(
                shape = RoundedCornerShape(30.dp),
                color = White700
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "만족도 체크하기",
                        modifier = Modifier.padding(start = 35.dp, top = 7.dp, bottom = 7.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pretendardFamily
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = if (expandSatisfaction.value) {
                            painterResource(id = R.drawable.ic_white_down_16)
                        } else {
                            painterResource(id = R.drawable.ic_next_white_16)
                        },
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            expandSatisfaction.value = !expandSatisfaction.value
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "# 태그하기",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, bottom = 69.dp),
            color = Grey100,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = pretendardFamily
        )
    }
}