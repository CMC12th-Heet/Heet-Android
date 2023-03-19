package org.heet.presentation.home.hometown.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.heet.R
import org.heet.components.Back
import org.heet.components.DotDivider
import org.heet.components.Title
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun DetailScreen(
    post_id: String,
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val expandSatisfaction = remember { mutableStateOf(false) }
    val expandShare = remember { mutableStateOf(false) }
    val detailPost = detailViewModel.detail.collectAsState().value
    val deleteSuccess by detailViewModel.deleteSuccess.collectAsState()
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true, key2 = deleteSuccess) {
        detailViewModel.getDetailPost(post_id)
        if (deleteSuccess) {
            navController.popBackStack()
        }
    }

    if (detailPost != null) {
        Surface(modifier = Modifier.fillMaxSize()) {
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
                    Back {
                        navController.popBackStack()
                    }
                    Title(text = "우리동네 기록")
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                isDropDownMenuExpanded = true
                            }
                        )
                        MaterialTheme(
                            shapes = MaterialTheme.shapes.copy(
                                medium = RoundedCornerShape(
                                    10.dp
                                )
                            )
                        ) {
                            DropdownMenu(
                                expanded = isDropDownMenuExpanded,
                                onDismissRequest = { isDropDownMenuExpanded = false }
                            ) {
                                if (detailPost.isMyPost == 1) {
                                    DropdownMenuItem(onClick = {
                                    }) {
                                        Text(
                                            text = "수정하기",
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = pretendardFamily,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                    DropdownMenuItem(onClick = {
                                    }) {
                                        Text(
                                            text = "삭제하기",
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = pretendardFamily,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    detailViewModel.deletePost(post_id)
                                                }
                                        )
                                    }
                                } else {
                                    DropdownMenuItem(onClick = {
                                        navController.navigate(
                                            HomeTownScreen.Declaration.route
                                        )
                                    }) {
                                        Text(
                                            text = "신고하기",
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = pretendardFamily,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(30.dp),
                    color = Red500,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp)
                ) {
                    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_detail_local),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = detailPost.store.name,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = pretendardFamily
                        )
                    }
                }
                Spacer(modifier = Modifier.height(17.5.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Surface(shape = RoundedCornerShape(5.dp)) {
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(model = detailPost.urlList[0]),
                                contentDescription = "image",
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height = 225.dp)
                            )

                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = Color.White,
                                modifier = Modifier
                                    .padding(end = 12.dp, bottom = 11.dp)
                                    .align(Alignment.BottomEnd)
                            ) {
                                Text(
                                    text = "1/${detailPost.urlList.size}",
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

                    Text(
                        text = detailPost.title,
                        color = Grey550,
                        fontSize = 17.sp,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    DotDivider()
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = detailPost.mini_title,
                        fontSize = 12.sp,
                        color = Grey550,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    DotDivider()
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        modifier = Modifier.height(73.dp),
                        text = detailPost.content,
                        fontSize = 12.sp,
                        color = Grey1000,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    DotDivider()
                    Spacer(modifier = Modifier.height(13.dp))
                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = Black400
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "꿀팁 정보들",
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    top = 7.dp,
                                    bottom = 7.dp
                                ),
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
                        color = Black400
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "만족도 체크",
                                modifier = Modifier.padding(
                                    start = 35.dp,
                                    top = 7.dp,
                                    bottom = 7.dp
                                ),
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
                    text = "#",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, bottom = 69.dp),
                    color = Grey100,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = pretendardFamily
                )
            }
            Box(contentAlignment = Alignment.BottomCenter) {
                Column {
                    Divider(
                        modifier = Modifier
                            .height(0.5.dp)
                            .shadow(2.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_detail_comment),
                                contentDescription = "comment",
                                modifier = Modifier.clickable {
                                    navController.navigate(HomeTownScreen.Comment.withArgs(post_id))
                                }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_detail_bookmark),
                                contentDescription = "bookmark"
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_detail_share),
                            contentDescription = "share"
                        )
                    }
                }
            }
        }
    }
}
