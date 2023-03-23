package org.heet.presentation.home.following.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import org.heet.components.DotDivider
import org.heet.core.navigation.navscreen.FollowingScreen
import org.heet.ui.theme.Black400
import org.heet.ui.theme.Grey250
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Red500
import org.heet.util.pretendardFamily

@Composable
fun FollowingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 65.dp)
    ) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Surface(
                    modifier = Modifier.clickable {
                        navController.navigate(FollowingScreen.FollowingList.route)
                    },
                    shape = RoundedCornerShape(30.dp),
                    color = Black400
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "팔로잉 목록",
                            modifier = Modifier.padding(
                                start = 40.dp,
                                top = 6.5.dp,
                                bottom = 6.5.dp
                            ),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = pretendardFamily
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_next_white_16),
                            contentDescription = "following_list",
                            modifier = Modifier.padding(start = 30.dp, end = 18.5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "최근 24시간",
                        color = Grey250,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = pretendardFamily
                    )
                    Spacer(modifier = Modifier.padding(end = 3.dp))
                    DotDivider()
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            items(listOf(1, 2, 3, 4, 5)) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, start = 10.dp, end = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_profile_grey_22),
                                    contentDescription = "profile"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "heet_member",
                                    color = Grey400,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = pretendardFamily
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "카페 704",
                                    color = Red500,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black,
                                    fontFamily = pretendardFamily
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_location_red_18),
                                    contentDescription = "location"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.img_default_hometown),
                                contentDescription = "image",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 12.dp, bottom = 11.dp)
                                    .align(Alignment.BottomStart)
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
                            Image(
                                painter = painterResource(id = R.drawable.ic_white_bookmark_50),
                                contentDescription = "bookmark",
                                modifier = Modifier.align(Alignment.BottomEnd)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "이전 게시물",
                        color = Grey250,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = pretendardFamily
                    )
                    Spacer(modifier = Modifier.padding(end = 5.dp))
                    DotDivider()
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            items(listOf(1, 2, 3, 4, 5)) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, start = 10.dp, end = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_profile_grey_22),
                                    contentDescription = "profile"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "heet_member",
                                    color = Grey400,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = pretendardFamily
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "카페 704",
                                    color = Red500,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black,
                                    fontFamily = pretendardFamily
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_location_red_18),
                                    contentDescription = "location"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.img_default_hometown),
                                contentDescription = "image",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 12.dp, bottom = 11.dp)
                                    .align(Alignment.BottomStart)
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
                            Image(
                                painter = painterResource(id = R.drawable.ic_white_bookmark_50),
                                contentDescription = "bookmark",
                                modifier = Modifier.align(Alignment.BottomEnd)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
