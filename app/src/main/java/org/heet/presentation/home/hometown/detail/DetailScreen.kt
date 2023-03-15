package org.heet.presentation.home.hometown.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    post_id: String?,
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val expandSatisfaction = remember { mutableStateOf(false) }
    val expandShare = remember { mutableStateOf(false) }
    val detailPost = detailViewModel.detail.collectAsState()

    if (post_id != null) {
        detailViewModel.getDetailPost(post_id)
    }

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
            Image(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.navigate(HomeTownScreen.Declaration.route)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            shape = RoundedCornerShape(30.dp),
            color = Red500,
            modifier = Modifier.align(Alignment.Start).padding(start = 20.dp)
        ) {
            val store = if (detailPost.value == null) "" else "${detailPost.value?.store?.name}"

            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_detail_local),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = store,
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
            val image =
                if (detailPost.value == null) painterResource(id = R.drawable.img_default_post)
                else rememberAsyncImagePainter(model = detailPost.value!!.urlList[0])
            val text =
                if (detailPost.value == null) "1/7" else "1/${detailPost.value?.urlList?.size}"
            val title = if (detailPost.value == null) "" else "${detailPost.value?.title}"
            val subtitle = if (detailPost.value == null) "" else "${detailPost.value?.mini_title}"
            val content = if (detailPost.value == null) "" else "${detailPost.value?.content}"

            Surface(shape = RoundedCornerShape(5.dp)) {
                Box {
                    Image(
                        painter = image,
                        contentDescription = "image",
                        contentScale = ContentScale.FillWidth,
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
                            text = text,
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
                text = title,
                color = Grey550,
                fontSize = 17.sp,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
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
                text = content,
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
                color = Black400
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "만족도 체크",
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
}
