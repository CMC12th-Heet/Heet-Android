package org.heet.presentation.home.hometown.modifypost

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.Graph
import org.heet.data.datasource.LoadSatisfactionDataSource
import org.heet.data.model.response.ResponseGetDetailPost
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun ModifyPostScreen(
    navController: NavController,
    postId: String,
    modifyPostViewModel: ModifyPostViewModel = hiltViewModel(),
) {
    modifyPostViewModel.getDetailPost(postId)

    // post
    val title = remember { mutableStateOf("") }
    val subTitle = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }

    // satisfaction
    val expandSatisfaction = remember { mutableStateOf(false) }
    val satisfaction = remember { mutableStateOf(0) }

    // shareTip
    val expandShare = remember { mutableStateOf(false) }
    val togetherWith = remember { mutableStateOf("") }
    val dayTip = remember { mutableStateOf("") }
    val movingTip = remember { mutableStateOf("") }
    val orderingTip = remember { mutableStateOf("") }
    val otherTip = remember { mutableStateOf("") }

    val postContent = modifyPostViewModel.postContent.collectAsState().value

    LaunchedEffect(modifyPostViewModel.updateSuccess.collectAsState().value) {
        if (modifyPostViewModel.updateSuccess.value) {
            navController.navigate(Graph.HOME) {
                popUpTo(0)
            }
        }
    }

    if (postContent != null) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            TopBar(
                navController,
                modifyPostViewModel,
                postId,
                title,
                subTitle,
                content,
                satisfaction,
                togetherWith,
                dayTip,
                movingTip,
                orderingTip,
                otherTip,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Location(postContent)
            Spacer(modifier = Modifier.height(10.dp))
            ImageList(postContent, modifyPostViewModel)
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            FlatModifyTextField(
                valueState = title,
                placeholder = postContent.title,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Start),
                textStyle = TextStyle.Default.copy(
                    color = Grey1300,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = pretendardFamily,
                ),
            )
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            FlatModifyTextField(
                valueState = subTitle,
                placeholder = postContent.mini_title,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Start),
                textStyle = TextStyle.Default.copy(
                    color = White550,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendardFamily,
                ),
            )
            Spacer(modifier = Modifier.height(6.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(6.dp))
            FlatModifyTextField(
                valueState = content,
                placeholder = postContent.content,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Start),
                textStyle = TextStyle.Default.copy(
                    color = Grey1000,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendardFamily,
                ),
            )
            Spacer(modifier = Modifier.height(36.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(18.dp))
            ShareTip(expandShare)
            TipList(
                modifier = Modifier.align(Alignment.Start),
                expandShare = expandShare,
                togetherWith,
                dayTip,
                movingTip,
                orderingTip,
                otherTip,
            )
            Spacer(modifier = Modifier.height(14.dp))
            Divider(
                modifier = Modifier
                    .height(0.5.dp)
                    .shadow(2.dp),
                color = White00,
            )
            Spacer(modifier = Modifier.height(13.dp))
            Satisfaction(expandSatisfaction)
            Spacer(modifier = Modifier.height(24.dp))
            SatisfactionList(expandSatisfaction, satisfaction)
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
private fun SatisfactionList(
    expandSatisfaction: MutableState<Boolean>,
    satisfaction: MutableState<Int>,
) {
    if (expandSatisfaction.value) {
        var satisfactionList by remember { mutableStateOf(LoadSatisfactionDataSource().loadSatisfactionItems()) }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            for (index in satisfactionList.indices) {
                val color = if (satisfactionList[index].isSelected) {
                    Red500
                } else {
                    Grey400
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = satisfactionList[index].image),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color),
                        modifier = Modifier.clickable {
                            satisfactionList =
                                satisfactionList.mapIndexed { j, satisfactionItem ->
                                    if (index == j) {
                                        satisfaction.value = j + 1
                                        satisfactionItem.copy(isSelected = !satisfactionItem.isSelected)
                                    } else {
                                        satisfactionItem.copy(isSelected = false)
                                    }
                                }
                        },
                    )
                    Text(
                        text = satisfactionList[index].text,
                        color = if (satisfactionList[index].isSelected) {
                            Red500
                        } else {
                            Grey400
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = pretendardFamily,
                    )
                }
            }
        }
    }
}

@Composable
private fun TipList(
    modifier: Modifier = Modifier,
    expandShare: MutableState<Boolean>,
    togetherWith: MutableState<String>,
    dayTip: MutableState<String>,
    movingTip: MutableState<String>,
    orderingTip: MutableState<String>,
    otherTip: MutableState<String>,
) {
    if (expandShare.value) {
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "*공유하고 싶은 꿀팁을 눌러 활성화하세요.",
                color = Red500,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = pretendardFamily,
            )
            Spacer(modifier = Modifier.height(17.dp))
            ShareTip(
                "누구와 함께해요!",
                "ex) 같이 간 사람/가고 싶은 사람",
                Modifier.align(Alignment.Start),
                16.5.dp,
                shareTip = togetherWith,
            )
            ShareTip(
                "이런 날 방문해요!",
                "ex) 생일/기념일/기분 꿀꿀한 날.",
                Modifier.align(Alignment.Start),
                15.5.dp,
                shareTip = dayTip,
            )
            ShareTip(
                "이동 꿀팁",
                "ex) 3번 출구 바로 앞 골목이 지름길!",
                Modifier.align(Alignment.Start),
                shareTip = movingTip,
            )
            ShareTip(
                "주문 꿀팁",
                "ex) 시그니처 라떼는 필수입니다.",
                Modifier.align(Alignment.Start),
                shareTip = orderingTip,
            )
            ShareTip(
                "기타 꿀팁",
                "ex) 화장실 문고리 잘 흔들려요!",
                Modifier.align(Alignment.Start),
                shareTip = otherTip,
            )
        }
    }
}

@Composable
private fun TopBar(
    navController: NavController,
    modifyPostViewMode: ModifyPostViewModel,
    postId: String,
    title: MutableState<String>,
    subTitle: MutableState<String>,
    content: MutableState<String>,
    satisfaction: MutableState<Int>,
    togetherWith: MutableState<String>,
    dayTip: MutableState<String>,
    movingTip: MutableState<String>,
    orderingTip: MutableState<String>,
    otherTip: MutableState<String>,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Cancel(modifier = Modifier.clickable { navController.popBackStack() })
        Title(title = "우리동네 기록")
        Finish(
            modifier = Modifier.clickable {
                modifyPostViewMode.updatePost(
                    postId,
                    title.value,
                    subTitle.value,
                    content.value,
                    satisfaction.value,
                    togetherWith.value,
                    dayTip.value,
                    movingTip.value,
                    orderingTip.value,
                    otherTip.value,
                )
            },
        )
    }
}

@Composable
private fun Location(postContent: ResponseGetDetailPost) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Black400,
    ) {
        Row(
            modifier = Modifier.padding(
                start = 13.dp,
                top = 5.dp,
                end = 52.dp,
                bottom = 5.dp,
            ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_white_location_20),
                contentDescription = "location",
            )
            Spacer(modifier = Modifier.width(39.dp))
            Text(
                text = postContent.store.name,
                color = White00,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                fontFamily = pretendardFamily,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImageList(
    postContent: ResponseGetDetailPost,
    modifyPostViewModel: ModifyPostViewModel,
) {
    HorizontalPager(
        postContent.urlList.size,
        modifier = Modifier.padding(horizontal = 20.dp),
    ) { index ->
        Surface(shape = RoundedCornerShape(5.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                SubcomposeAsyncImage(
                    model = postContent.urlList[index],
                    contentDescription = "image",
                    modifier = Modifier
                        .width(
                            modifyPostViewModel.resolutionMetrics.toDP(modifyPostViewModel.resolutionMetrics.screenWidth).dp -
                                40.dp,
                        )
                        .height(256.dp),
                    loading = {
                        CircularProgressIndicator()
                    },
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = White00,
                    modifier = Modifier
                        .padding(end = 11.dp, bottom = 10.dp)
                        .align(Alignment.BottomEnd),
                ) {
                    Text(
                        text = "${index + 1}/${postContent.urlList.size}",
                        modifier = Modifier.padding(
                            start = 17.dp,
                            top = 2.dp,
                            end = 15.dp,
                            bottom = 3.dp,
                        ),
                        color = Black400,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = pretendardFamily,
                    )
                }
            }
        }
    }
}

@Composable
private fun ShareTip(expandShare: MutableState<Boolean>) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Black400,
    ) {
        Row(
            modifier = Modifier.padding(
                start = 15.5.dp,
                top = 7.dp,
                end = 15.5.dp,
                bottom = 7.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "꿀팁 정보들 공유하기",
                color = White00,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily,
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
                },
            )
        }
    }
}

@Composable
private fun Satisfaction(expandSatisfaction: MutableState<Boolean>) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Black400,
    ) {
        Row(
            modifier = Modifier.padding(
                start = 27.dp,
                top = 7.dp,
                end = 13.5.dp,
                bottom = 7.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "만족도 체크하기",
                color = White00,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily,
            )
            Spacer(modifier = Modifier.width(15.5.dp))
            Image(
                painter = if (expandSatisfaction.value) {
                    painterResource(id = R.drawable.ic_white_down_16)
                } else {
                    painterResource(id = R.drawable.ic_next_white_16)
                },
                contentDescription = "satisfaction",
                modifier = Modifier.clickable {
                    expandSatisfaction.value = !expandSatisfaction.value
                },
            )
        }
    }
}
