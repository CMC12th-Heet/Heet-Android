package org.heet.presentation.home.mypage.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import org.heet.core.navigation.navscreen.MyPageScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun MyPage(
    navController: NavController,
    myPageViewModel: MyPageViewModel = hiltViewModel()
) {
    val existPost = remember { mutableStateOf(false) }
    val username = remember { mutableStateOf("") }
    val town = remember { mutableStateOf("") }
    val isVerify = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        myPageViewModel.getMyPage()
        myPageViewModel.profile.collect {
            username.value = it.username
            town.value = it.town
            isVerify.value = it.is_verify
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
            elevation = 20.dp
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, top = 18.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_text_logo_67),
                        contentDescription = null
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bookmark_grey_44),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                navController.navigate(MyPageScreen.Scrap.route)
                            }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_setting_grey_44),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                navController.navigate(MyPageScreen.Setting.route)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(31.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_grey_55),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = username.value,
                    color = Grey350,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(9.dp))
                Text(
                    text = "???????????? ??????????????????",
                    color = White650,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location_red_15),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = town.value,
                        color = Red500,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (isVerify.value) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_local_red_27),
                            contentDescription = null
                        )
                    } else {
                        Spacer(modifier = Modifier.width(26.dp))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "????????? 0",
                        color = Grey850,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier.size(width = 1.dp, height = 12.dp),
                        color = Grey750
                    )
                    Text(
                        text = "????????? 0",
                        color = Grey850,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier.size(width = 1.dp, height = 12.dp),
                        color = Grey750
                    )
                    Text(
                        text = "????????? 0",
                        color = Grey850,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                if (existPost.value) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 65.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy((-13).dp)
                    ) {
                        items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) {
                            Surface(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.padding(top = 18.dp)
                            ) {
                                Box {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_default_scrap),
                                        contentDescription = "image",
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(start = 6.dp, top = 7.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_location_white_16),
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "?????? ?????????",
                                            color = Color.White,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = pretendardFamily
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!existPost.value) {
            Image(
                painter = painterResource(id = R.drawable.img_notify),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    .padding(start = 38.dp, end = 38.dp, bottom = 141.dp)
            )
        }
    }
}
