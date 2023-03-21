package org.heet.presentation.signup.residence

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.SignUpScreen
import org.heet.data.datasource.LocalHometownDataSource
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Grey900
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White50
import org.heet.util.pretendardFamily

@Composable
fun ResidenceScreen(
    navController: NavController,
    residenceViewModel: ResidenceViewModel = hiltViewModel()
) {
    val cityName = remember { mutableStateOf("") }
    val wardName = remember { mutableStateOf("") }
    val checkCity = remember { mutableStateOf(false) }
    val checkWard = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title(title = "동네 설정하기")
                EmptyText()
            }
            Row(modifier = Modifier.padding(start = 28.dp, top = 40.dp)) {
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
            LazyColumn(modifier = Modifier.padding(top = 18.dp)) {
                items(LocalHometownDataSource().loadHometowns()) { city ->
                    val expanded = remember { mutableStateOf(false) }
                    val image = if (expanded.value) {
                        painterResource(id = R.drawable.ic_down_grey_24)
                    } else {
                        painterResource(id = R.drawable.ic_next_grey_24)
                    }
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CityItem(
                            city.cityKr,
                            image,
                            expanded,
                            checkWard,
                            checkCity,
                            cityName
                        )
                    }
                    if (expanded.value) {
                        var where = listOf<String>()
                        when (city.cityEng) {
                            "seoul" -> {
                                residenceViewModel.getSeoulCity(city.cityEng)
                                where = residenceViewModel.seoul.collectAsState().value
                            }
                            "gyeonggi" -> {
                                residenceViewModel.getGyeonggiCity(city.cityEng)
                                where = residenceViewModel.gyeonggi.collectAsState().value
                            }
                            "incheon" -> {
                                residenceViewModel.getIncheonCity(city.cityEng)
                                where = residenceViewModel.incheon.collectAsState().value
                            }
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        LazyRow(modifier = Modifier.padding(start = 30.dp)) {
                            items(
                                where
                            ) { ward ->
                                WardItem(
                                    updateResidence = { residenceViewModel.updateResidence("${city.cityKr} $ward") },
                                    ward,
                                    city.cityKr,
                                    checkWard,
                                    wardName,
                                    cityName
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.5.dp))
                        Divider(modifier = Modifier.height(0.5.dp).shadow(2.dp))
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
        if (checkCity.value && checkWard.value) {
            SettingButton() {
                navController.navigate(SignUpScreen.Welcome.route)
            }
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
        modifier = Modifier.padding(start = 30.dp),
        color = Grey400,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily
    )
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.padding(end = 27.dp).clickable {
            expanded.value = !expanded.value
            checkWard.value = false
            checkCity.value = true
            cityName.value = city
        }
    )
}

@Composable
private fun WardItem(
    updateResidence: () -> Unit,
    ward: String,
    city: String,
    checkWard: MutableState<Boolean>,
    wardName: MutableState<String>,
    cityName: MutableState<String>
) {
    Text(
        ward,
        color = Grey400,
        modifier = Modifier.padding(end = 14.dp).clickable {
            updateResidence()
            wardName.value = ward
            checkWard.value = true
            cityName.value = city
        },
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily
    )
}

@Composable
private fun ResidenceChip(name: MutableState<String>) {
    Surface(
        shape = RoundedCornerShape(16.5.dp),
        color = White50
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(11.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_check_red_12),
                contentDescription = null
            )
            Text(
                text = name.value,
                modifier = Modifier.padding(start = 10.dp, top = 6.dp, end = 14.dp, bottom = 6.dp),
                color = Red500,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
        }
    }
}

@Composable
private fun NoticeResidence() {
    Text(
        text = "현재 거주지를 알려주세요",
        modifier = Modifier.padding(start = 30.dp),
        color = Grey900,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = pretendardFamily
    )
}

@Composable
private fun SettingButton(onClick: () -> Unit) {
    RedBigRoundButton28(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
        text = "설정 완료",
        onClick = onClick
    )
}
