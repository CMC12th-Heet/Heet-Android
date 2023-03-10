package org.heet.presentation.home.hometown.address

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.data.datasource.LocalSearchHelpDataSource
import org.heet.data.model.request.RequestPostStore
import org.heet.data.model.response.ResponseGetStore
import org.heet.data.model.response.SearchResult
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val address = remember {
        mutableStateOf("")
    }
    val didSearch = remember {
        mutableStateOf(false)
    }
    val storeList = addressViewModel.store.collectAsState()
    val storeName = remember {
        mutableStateOf("")
    }
    val storeUrl = remember {
        mutableStateOf("")
    }
    val storeAddress = remember {
        mutableStateOf("")
    }
    if (addressViewModel.updateSuccess.collectAsState().value) {
        navController.popBackStack(route = HomeTownScreen.Post.route, inclusive = false)
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
                    painter = painterResource(id = R.drawable.ic_cancel_black_30),
                    contentDescription = "cancel",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Title(text = "???????????? ??????")
                if (didSearch.value) {
                    Text(
                        text = "??????",
                        modifier = Modifier.clickable {
                            addressViewModel.postStore(
                                RequestPostStore(
                                    storeName.value,
                                    storeUrl.value,
                                    storeAddress.value
                                )
                            )
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
                addressViewModel = addressViewModel
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
            text = "????????????",
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
            text = "???????????? ??????????????? ????????? ??????????????????.",
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
    addressViewModel: AddressViewModel
) {
    BasicTextField(
        value = address.value,
        onValueChange = {
            if (it.length <= 20) {
                address.value = it
                addressViewModel.searchStore(address.value)
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
                            text = "?????? ??????",
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
