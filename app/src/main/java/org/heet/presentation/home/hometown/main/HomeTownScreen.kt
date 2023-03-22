package org.heet.presentation.home.hometown.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.rememberAsyncImagePainter
import org.heet.R
import org.heet.components.DotDivider
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.data.model.response.ResponseGetPost
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun HomeTownScreen(
    navController: NavController,
    homeTownViewModel: HomeTownViewModel = hiltViewModel()
) {
    homeTownViewModel.getNewPost()
    homeTownViewModel.getMyPage()
    val town = homeTownViewModel.profile.collectAsState().value?.town
    val isNewPost = homeTownViewModel.isNewPost.collectAsState()
    val post = if (isNewPost.value) homeTownViewModel.newPost.collectAsState()
    else homeTownViewModel.hotPost.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            TopBar(navController = navController, town = town)
            Divider(modifier = Modifier.padding(top = 9.dp), color = White100)
            Filter(homeTownViewModel = homeTownViewModel)
            Divider(color = White100)
            ContentList(navController = navController, post = post)
        }
    }
}

@Composable
private fun ContentList(navController: NavController, post: State<List<ResponseGetPost>>) {
    LazyColumn(
        modifier = Modifier.padding(
            start = 20.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 65.dp
        )
    ) {
        items(post.value) {
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
                                text = it.title,
                                modifier = Modifier.padding(top = 11.dp),
                                fontSize = 16.sp,
                                color = Black100,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pretendardFamily
                            )
                            Text(
                                text = it.mini_title,
                                modifier = Modifier.padding(top = 4.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = pretendardFamily
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_local_red_28),
                            contentDescription = "local",
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate(HomeTownScreen.Detail.withArgs("${it.post_id}"))
                        }
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = it.urlList[0]),
                            contentDescription = "image",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxWidth().height(height = 225.dp)
                        )
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 12.dp, bottom = 11.dp)
                                .align(Alignment.BottomEnd)
                        ) {
                            Text(
                                text = "1/${it.urlList.size}",
                                modifier = Modifier.padding(horizontal = 13.dp),
                                color = Black400,
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
                        painter = painterResource(id = R.drawable.ic_profile_grey_27),
                        contentDescription = "profile"
                    )
                    Text(text = it.user.username, modifier = Modifier.padding(start = 7.dp))
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_comment_grey_19),
                        contentDescription = "comment",
                        modifier = Modifier.padding(end = 22.dp).clickable {
                            navController.navigate(HomeTownScreen.Comment.route)
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_bookmark_grey_17),
                        contentDescription = "bookmark",
                        modifier = Modifier.padding(end = 3.dp)
                    )
                }
            }
            Text(
                text = it.created_at,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontSize = 10.sp,
                color = White300,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun TopBar(navController: NavController, town: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(13.dp),
            color = Red500
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_location_white_17),
                    contentDescription = null
                )
                Text(
                    text = town ?: "",
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
                painter = painterResource(id = R.drawable.ic_search_grey_44),
                contentDescription = "search",
                modifier = Modifier.clickable {
                    navController.navigate(HomeTownScreen.FindUser.route)
                }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_grey_bookmark_44),
                contentDescription = "bookmark",
                modifier = Modifier.clickable {
                    navController.navigate(HomeTownScreen.Scrap.route)
                }
            )
        }
    }
}

@Composable
private fun Filter(homeTownViewModel: HomeTownViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_grey_filter_25),
            contentDescription = null
        )
        Divider(
            modifier = Modifier
                .padding(start = 5.dp, end = 9.dp)
                .width(1.dp)
                .height(11.dp),
            color = Grey750
        )
        Text(
            text = "최신순",
            color = Red500,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = pretendardFamily,
            modifier = Modifier.clickable {
                homeTownViewModel.getNewPost()
            }
        )
        Divider(
            modifier = Modifier
                .padding(start = 16.dp, end = 15.dp)
                .width(1.dp)
                .height(11.dp),
            color = Grey750
        )
        Text(
            text = "베스트순",
            color = Red500,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = pretendardFamily,
            modifier = Modifier.clickable {
                homeTownViewModel.getHotPost()
            }
        )
    }
}
