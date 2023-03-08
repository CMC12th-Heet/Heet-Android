package org.heet.presentation.home.mypage

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
import androidx.navigation.NavController
import org.heet.R
import org.heet.core.navigation.navscreen.DetailScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun MyPage(navController: NavController) {
    val existPost = remember { mutableStateOf(false) }
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
                        painter = painterResource(id = R.drawable.ic_mypage_logo),
                        contentDescription = null
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_mypage_bookmark),
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_mypage_setting),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                navController.navigate(DetailScreen.Setting.route)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(31.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_mypage_default_profile),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "abcdef112",
                    color = Black1200,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(9.dp))
                Text(
                    text = "소개글을 작성해주세요",
                    color = Grey1300,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_mypage_profile_location),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "중구 약수동",
                        color = Red200,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_mypage_local),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "게시글 0",
                        color = Black1400,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier.size(width = 1.dp, height = 12.dp),
                        color = Black400
                    )
                    Text(
                        text = "팔로잉 0",
                        color = Black1400,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier.size(width = 1.dp, height = 12.dp),
                        color = Black400
                    )
                    Text(
                        text = "팔로워 0",
                        color = Black1400,
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
                                        painter = painterResource(id = R.drawable.img_scrap_default),
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
                                            painter = painterResource(id = R.drawable.ic_mypage_location),
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "중구 약수동",
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
                painter = painterResource(id = R.drawable.img_alert),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    .padding(start = 38.dp, end = 38.dp, bottom = 141.dp)
            )
        }
    }
}
