package org.heet.presentation.home.hometown.post

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import org.heet.R
import org.heet.components.*
import org.heet.data.datasource.LocalSearchHelpDataSource
import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.ResponseGetStore
import org.heet.data.model.response.SearchResult
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
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
    val bitmap = remember { mutableListOf<Bitmap>() }
    val showDialog = remember { mutableStateOf(false) }

    val friend = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val openAddress = remember { mutableStateOf(false) }
    val address = remember { mutableStateOf("") }
    val didSearch = remember { mutableStateOf(false) }
    val storeList = postViewModel.store.collectAsState()
    val storeName = remember { mutableStateOf("") }
    val storeUrl = remember { mutableStateOf("") }
    val storeAddress = remember { mutableStateOf("") }
    val selectStore = postViewModel.storeName.collectAsState()

    if (showDialog.value) {
        WriteCancel(showDialog = showDialog, navController = navController, postViewModel = postViewModel)
    }

    LaunchedEffect(key1 = true) {
        TedImagePicker.with(context)
            .mediaType(MediaType.IMAGE)
            .buttonText("완료")
            .min(1, "1개는 필수입니다.")
            .max(10, "10개가 최대입니다.")
            .startMultiImage {
                imageUri = it
            }
    }

    LaunchedEffect(postViewModel.postSuccess.collectAsState().value) {
        if (postViewModel.postSuccess.value) {
            navController.popBackStack()
        }
    }

    if (openAddress.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cancel_black_30),
                        contentDescription = "cancel",
                        modifier = Modifier.clickable {
                            openAddress.value = false
                        }
                    )
                    Title(text = "우리동네 기록")
                    if (didSearch.value) {
                        Text(
                            text = "등록",
                            modifier = Modifier.clickable {
                                postViewModel.postStore(
                                    RequestPostStore(
                                        storeName.value,
                                        storeUrl.value,
                                        storeAddress.value
                                    )
                                ).run {
                                    openAddress.value = false
                                }
                            },
                            color = Red500,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = pretendardFamily
                        )
                    } else {
                        EmptyText()
                    }
                }
                AddressTextField(
                    address = address,
                    keyboardController = keyboardController,
                    postViewModel = postViewModel
                )
                Spacer(modifier = Modifier.height(11.dp))
                if (address.value.isNotEmpty()) {
                    AddressInputResult(
                        didSearch = didSearch,
                        searchResult = storeList,
                        storeName = storeName,
                        storeAddress = storeAddress,
                        storeUrl = storeUrl
                    )
                } else {
                    AddressInputGuide()
                }
            }
        }
    } else {
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
                        showDialog.value = true
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
                    openAddress.value = true
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Timber.d(postViewModel.getSelectStore())
                    Text(
                        text = if (selectStore.value == "") "주소를 등록해주세요" else selectStore.value,
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
                imageUri?.let {
                    for (image in imageUri!!) {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.add(
                                MediaStore.Images.Media.getBitmap(
                                    context.contentResolver,
                                    image
                                )
                            )
                        } else {
                            val source =
                                ImageDecoder.createSource(context.contentResolver, image)
                            bitmap.add(ImageDecoder.decodeBitmap(source))
                        }
                    }
                }
                LazyRow {
                    if (bitmap.size != 0) {
                        items(bitmap.size) {
                            Surface(shape = RoundedCornerShape(5.dp)) {
                                Box {
                                    Image(
                                        bitmap = bitmap[it].asImageBitmap(),
                                        contentDescription = "image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .width(380.dp)
                                            .height(256.dp)
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(end = 12.dp, bottom = 11.dp)
                                            .align(Alignment.BottomEnd)
                                    ) {
                                        Text(
                                            text = "${it + 1}/${imageUri?.size}",
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
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth(),
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
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 7.dp
                                    ),
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
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
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
}

@Composable
private fun AddressInputResult(
    didSearch: MutableState<Boolean>,
    searchResult: State<List<ResponseGetStore>>,
    storeName: MutableState<String>,
    storeUrl: MutableState<String>,
    storeAddress: MutableState<String>
) {
    var searchResultList by remember {
        mutableStateOf(emptyList<SearchResult>())
    }
    searchResultList = searchResult.value.map {
        SearchResult(it.place_name, it.address_name, it.place_url, false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "검색결과",
            modifier = Modifier.padding(start = 15.dp),
            color = Black700,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = pretendardFamily
        )
        Spacer(modifier = Modifier.height(11.dp))
        DotDivider()
        LazyColumn {
            items(searchResultList.size) { index ->
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    val image = if (searchResultList[index].isSelected) {
                        R.drawable.ic_location_red_20
                    } else {
                        R.drawable.ic_location_grey_20
                    }
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "address_location"
                    )
                    Column(modifier = Modifier.padding(start = 4.dp)) {
                        Text(
                            text = searchResultList[index].storeName,
                            color = if (searchResultList[index].isSelected) {
                                Red500
                            } else {
                                Grey400
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = pretendardFamily,
                            modifier = Modifier.clickable {
                                didSearch.value = true
                                searchResultList = searchResultList.mapIndexed { j, searchResult ->
                                    if (index == j) {
                                        searchResult.copy(isSelected = !searchResult.isSelected)
                                    } else {
                                        searchResult.copy(isSelected = false)
                                    }
                                }
                                storeName.value = searchResultList[index].storeName
                                storeAddress.value = searchResultList[index].address
                                storeUrl.value = searchResultList[index].url
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = searchResultList[index].address,
                            color = White800,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                DotDivider()
            }
        }
    }
}

@Composable
private fun AddressInputGuide() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "게시글을 작성하려는 장소를 검색해보세요.",
            color = Black700,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = pretendardFamily
        )
        Spacer(modifier = Modifier.height(11.dp))
        DotDivider()
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(LocalSearchHelpDataSource().loadSearchHelps()) {
                Text(
                    text = it.title,
                    color = Grey350,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = it.example,
                    color = Grey350,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Spacer(modifier = Modifier.height(13.dp))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun AddressTextField(
    address: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    postViewModel: PostViewModel
) {
    BasicTextField(
        value = address.value,
        onValueChange = {
            if (it.length <= 20) {
                address.value = it
                postViewModel.searchStore(address.value)
            }
        },
        modifier = Modifier.padding(top = 12.dp),
        enabled = true,
        textStyle = TextStyle.Default.copy(
            color = Black350,
            fontSize = 13.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            if (address.value.trim().isEmpty()) return@KeyboardActions
            keyboardController?.hide()
        },
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(30.dp), color = White700)
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                if (address.value.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "위치 검색",
                            fontFamily = pretendardFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_search_white_14),
                            contentDescription = "search"
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        innerTextField()
                        Image(
                            painter = painterResource(id = R.drawable.ic_circle_cancel_white_16),
                            contentDescription = "cancel",
                            modifier = Modifier.clickable { address.value = "" }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun WriteCancel(showDialog: MutableState<Boolean>, navController: NavController, postViewModel: PostViewModel) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            CancelWriteDialog(showDialog = showDialog) {
                navController.popBackStack()
                postViewModel.deleteSelectStore()
                postViewModel.deleteSelectStoreNum()
            }
        }
    }
}
