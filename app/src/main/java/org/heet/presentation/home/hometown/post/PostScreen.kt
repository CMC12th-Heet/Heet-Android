package org.heet.presentation.home.hometown.post

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun PostScreen(navController: NavController, postViewModel: PostViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val title = remember { mutableStateOf("") }
    val subTitle = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val expandSatisfaction = remember { mutableStateOf(false) }
    val expandShare = remember { mutableStateOf(false) }
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<List<Uri>?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val friend = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        TedImagePicker.with(context)
            .mediaType(MediaType.IMAGE)
            .buttonText("완료")
            .min(1, "1개는 필수입니다.")
            .max(10, "10개가 최대입니다.")
            .startMultiImage {
                imageUri = it
            }

        if (postViewModel.postSuccess.value) {
            navController.popBackStack()
        }
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
            Image(
                painter = painterResource(id = R.drawable.ic_cancel_black_30),
                contentDescription = "cancel",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    postViewModel.deleteSelectStore()
                    postViewModel.deleteSelectStoreNum()
                }
            )
            Title(text = "우리동네 기록")
            Finish {
                if (title.value.isEmpty() ||
                    subTitle.value.isEmpty() ||
                    content.value.isEmpty() ||
                    postViewModel.getSelectStoreNum() == -1
                ) {
                    Toast.makeText(context, "필요한 것을 채워 주세요", Toast.LENGTH_SHORT).show()
                } else {
                    imageUri?.let {
                        postViewModel.post(
                            context,
                            it,
                            title.value,
                            subTitle.value,
                            content.value,
                            postViewModel.getSelectStoreNum()
                        )
                    }
                }
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
                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(context.contentResolver, it[0])
                        } else {
                            val source =
                                ImageDecoder.createSource(context.contentResolver, it[0])
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }
                    }
                    bitmap.value?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "image",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.height(256.dp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 12.dp, bottom = 11.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(
                            text = "1/${imageUri?.size}",
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
            if (expandShare.value) {
                Column(
                    modifier = Modifier.align(Alignment.Start).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "공유하고 싶은 꿀팁을 눌러 활성화하세요.",
                        color = Red500,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = pretendardFamily
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    Row(modifier = Modifier.align(Alignment.Start)) {
                        Surface(
                            shape = RoundedCornerShape(30.dp),
                            color = White700
                        ) {
                            Text(
                                text = "누구와 함께해요!",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pretendardFamily
                            )
                        }
                        FlatTextField(
                            valueState = friend,
                            enabled = true,
                            isSingleLine = true,
                            placeholder = "ex) 동네 놀러온 찐친"
                        )
                    }
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
            if (expandSatisfaction.value) {
                Row(
                    modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_feel_worst),
                            contentDescription = null
                        )
                        Text(
                            text = "화나요.",
                            color = Grey250,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_feel_bad_46),
                            contentDescription = null
                        )
                        Text(
                            text = "별로에요.",
                            color = Grey250,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_feel_soso_46),
                            contentDescription = null
                        )
                        Text(
                            text = "그러적럭?",
                            color = Grey250,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_feel_good_46),
                            contentDescription = null
                        )
                        Text(
                            text = "좋았어요!",
                            color = Grey250,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_feel_awesome_46),
                            contentDescription = null
                        )
                        Text(
                            text = "재방문100%",
                            color = Grey250,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
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
