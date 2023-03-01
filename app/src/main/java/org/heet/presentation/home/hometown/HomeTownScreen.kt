package org.heet.presentation.home.hometown

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import org.heet.core.navigation.navscreen.DetailScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun HomeTownScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            TopBar()
            Divider(modifier = Modifier.padding(top = 9.dp), color = Grey900)
            Filter()
            Divider(color = Grey900)
            ContentList(navController)
        }
    }
}

@Composable
private fun ContentList(navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(
            start = 20.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 65.dp
        )
    ) {
        items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)) {
            DotDivider()
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text(
                                text = "약수 로컬 스콘 맛집 약수 로컬 스콘 맛집",
                                modifier = Modifier.padding(top = 11.dp),
                                fontSize = 16.sp,
                                color = Black500,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pretendardFamily
                            )
                            Text(
                                text = "삼삼삼베이커리",
                                modifier = Modifier.padding(top = 4.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = pretendardFamily
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_local),
                            contentDescription = "local",
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.img_default),
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
                                color = Black600,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = pretendardFamily
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(13.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_black_default_profile),
                        contentDescription = "profile"
                    )
                    Text(text = "heet_member", modifier = Modifier.padding(start = 7.dp))
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_list_comment),
                        contentDescription = "comment",
                        modifier = Modifier.padding(end = 22.dp).clickable {
                            navController.navigate(DetailScreen.Comment.route)
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_list_bookmark),
                        contentDescription = "bookmark",
                        modifier = Modifier.padding(end = 3.dp)
                    )
                }
            }
            Text(
                text = "2분 전",
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontSize = 10.sp,
                color = Grey1100,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(13.dp),
            color = Red200
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_hometown_location),
                    contentDescription = null
                )
                Text(
                    text = "서울시 용산구",
                    fontFamily = pretendardFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 4.dp,
                        end = 10.dp,
                        top = 5.5.dp,
                        bottom = 5.5.dp
                    )
                )
            }
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search"
            )
            Image(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "bookmark"
            )
        }
    }
}

@Composable
private fun Filter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = null
        )
        Divider(
            modifier = Modifier
                .padding(start = 5.dp, end = 9.dp)
                .width(1.dp)
                .height(11.dp),
            color = Black400
        )
        Text(
            text = "최신순",
            color = Red200,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = pretendardFamily
        )
        Divider(
            modifier = Modifier
                .padding(start = 16.dp, end = 15.dp)
                .width(1.dp)
                .height(11.dp),
            color = Black400
        )
        Text(
            text = "베스트순",
            color = Red200,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = pretendardFamily
        )
    }
}
