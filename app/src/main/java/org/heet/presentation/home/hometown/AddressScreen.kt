package org.heet.presentation.home.hometown

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.data.datasource.LocalSearchHelpDataSource
import org.heet.data.model.response.SearchResult
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressScreen(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val address = remember {
        mutableStateOf("")
    }
    val didSearch = remember {
        mutableStateOf(false)
    }

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
                    painter = painterResource(id = R.drawable.ic_cancel),
                    contentDescription = "cancel",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Title(text = "우리동네 기록")
                if (didSearch.value) {
                    Text(
                        text = "등록",
                        modifier = Modifier.clickable {
                        },
                        color = Red200,
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
                keyboardController = keyboardController
            )
            Spacer(modifier = Modifier.height(11.dp))
            if (address.value.isNotEmpty()) {
                AddressInputResult(didSearch = didSearch)
            } else {
                AddressInputGuide()
            }
        }
    }
}

@Composable
private fun AddressInputResult(didSearch: MutableState<Boolean>) {
    var searchResultList by remember {
        mutableStateOf(
            listOf(
                SearchResult(
                    "카페 704",
                    "서울특별시 동대문구 고산자로 56길 12",
                    false
                ),
                SearchResult(
                    "카페 704",
                    "서울특별시 동대문구 고산자로 56길 12",
                    false
                ),
                SearchResult(
                    "카페 704",
                    "서울특별시 동대문구 고산자로 56길 12",
                    false
                )
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "검색결과",
            modifier = Modifier.padding(start = 15.dp),
            color = Black1100,
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
                        R.drawable.ic_red_address_location
                    } else {
                        R.drawable.ic_grey_address_location
                    }
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "address_location"
                    )
                    Column(modifier = Modifier.padding(start = 4.dp)) {
                        Text(
                            text = searchResultList[index].storeName,
                            color = if (searchResultList[index].isSelected) {
                                Red200
                            } else {
                                Grey500
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
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = searchResultList[index].address,
                            color = Black1300,
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
            color = Black1100,
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
                    color = Black1200,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = it.example,
                    color = Black1200,
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
    keyboardController: SoftwareKeyboardController?
) {
    BasicTextField(
        value = address.value,
        onValueChange = {
            if (it.length <= 20) {
                address.value = it
            }
        },
        modifier = Modifier.padding(top = 12.dp),
        enabled = true,
        textStyle = TextStyle.Default.copy(
            color = Black1000,
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
                            painter = painterResource(id = R.drawable.ic_write_search),
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
                            painter = painterResource(id = R.drawable.ic_wrtie_cancel),
                            contentDescription = "cancel",
                            modifier = Modifier.clickable { address.value = "" }
                        )
                    }
                }
            }
        }
    )
}
