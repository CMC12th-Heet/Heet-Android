package org.heet.presentation.neighborhood

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.BigRoundButton
import org.heet.components.ScreenTitle
import org.heet.ui.theme.Grey800
import org.heet.ui.theme.Red400
import org.heet.util.pretendardFamily

@Composable
fun NeighborhoodSettingScreen(navController: NavController) {
    val cityName = remember {
        mutableStateOf("")
    }
    val wardName = remember {
        mutableStateOf("")
    }
    val checkCity = remember {
        mutableStateOf(false)
    }
    val checkWard = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            ScreenTitle(title = "동네 설정하기", modifier = Modifier.padding(start = 84.dp)) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(36.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                if (checkCity.value) {
                    ResidenceChip(cityName)
                    Spacer(modifier = Modifier.width(9.dp))
                    if (checkWard.value) {
                        ResidenceChip(wardName)
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoticeResidence()
            Spacer(modifier = Modifier.height(18.dp))
            LazyColumn {
                items(listOf("서울", "경기", "인천")) { city ->
                    val expanded = remember {
                        mutableStateOf(false)
                    }
                    val image = if (expanded.value) {
                        painterResource(id = R.drawable.ic_grey_down)
                    } else {
                        painterResource(id = R.drawable.ic_grey_next)
                    }
                    Row(
                        modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 7.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CityItem(city, image, expanded, checkWard, checkCity, cityName)
                    }
                    if (expanded.value) {
                        Spacer(modifier = Modifier.height(18.dp))
                        Divider(modifier = Modifier.height(0.5.dp).shadow(2.dp))
                        LazyRow(modifier = Modifier.padding(start = 11.dp)) {
                            items(
                                listOf(
                                    "종로구", "용산구", "종로구", "용산구", "종로구", "용산구",
                                    "종로구", "용산구", "종로구", "용산구", "종로구", "용산구"
                                )
                            ) { ward ->
                                WardItem(ward, checkWard, wardName)
                            }
                        }
                        Divider(modifier = Modifier.height(0.5.dp).shadow(2.dp))
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
        ) {
            SettingButton()
        }
    }
}

@Composable
private fun CityItem(
    city: String,
    image: Painter,
    expanded: MutableState<Boolean>,
    checkWard: MutableState<Boolean>,
    checkCity: MutableState<Boolean>,
    cityName: MutableState<String>
) {
    Text(
        text = city,
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Grey800
    )
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.clickable {
            expanded.value = !expanded.value
            checkWard.value = false
            checkCity.value = true
            cityName.value = city
        }
    )
}

@Composable
private fun WardItem(
    ward: String,
    checkWard: MutableState<Boolean>,
    wardName: MutableState<String>
) {
    Text(
        ward,
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Grey800,
        modifier = Modifier.clickable {
            checkWard.value = true
            wardName.value = ward
        }
    )
    Spacer(modifier = Modifier.width(24.dp))
}

@Composable
private fun ResidenceChip(name: MutableState<String>) {
    Surface(
        shape = RoundedCornerShape(16.5.dp),
        color = Red400,
        modifier = Modifier.size(width = 84.dp, height = 30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_white_check),
                contentDescription = null
            )
            Text(
                text = name.value,
                fontFamily = pretendardFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(start = 9.dp, end = 10.dp)
            )
        }
    }
}

@Composable
private fun NoticeResidence() {
    Text(
        text = "현재 거주지를 알려주세요",
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Red400,
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
private fun SettingButton() {
    BigRoundButton(
        onClick = { },
        text = "설정 완료"
    )
}
