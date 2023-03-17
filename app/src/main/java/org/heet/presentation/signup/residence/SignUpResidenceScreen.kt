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
fun SignUpResidenceScreen(
    navController: NavController,
    signUpResidenceViewModel: SignUpResidenceViewModel = hiltViewModel()
) {
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
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title(text = "동네 설정하기")
                EmptyText()
            }
            Row(modifier = Modifier.padding(start = 8.dp, top = 36.dp)) {
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
                    val expanded = remember {
                        mutableStateOf(false)
                    }
                    val image = if (expanded.value) {
                        painterResource(id = R.drawable.ic_down_grey_24)
                    } else {
                        painterResource(id = R.drawable.ic_next_grey_24)
                    }
                    Row(
                        modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 7.dp),
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
                                signUpResidenceViewModel.getSeoulCity(city.cityEng)
                                where = signUpResidenceViewModel.seoul.collectAsState().value
                            }
                            "gyeonggi" -> {
                                signUpResidenceViewModel.getGyeonggiCity(city.cityEng)
                                where = signUpResidenceViewModel.gyeonggi.collectAsState().value
                            }
                            "incheon" -> {
                                signUpResidenceViewModel.getIncheonCity(city.cityEng)
                                where = signUpResidenceViewModel.incheon.collectAsState().value
                            }
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Divider(modifier = Modifier.height(0.5.dp).shadow(2.dp))
                        LazyRow(modifier = Modifier.padding(start = 11.dp)) {
                            items(
                                where
                            ) { ward ->
                                WardItem(
                                    updateResidence = { signUpResidenceViewModel.updateResidence("${city.cityKr} $ward") },
                                    ward,
                                    checkWard,
                                    wardName
                                )
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
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Grey400
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
    updateResidence: () -> Unit,
    ward: String,
    checkWard: MutableState<Boolean>,
    wardName: MutableState<String>
) {
    Text(
        ward,
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Grey400,
        modifier = Modifier.clickable {
            updateResidence()
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
        color = White50,
        modifier = Modifier.size(width = 84.dp, height = 30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_check_red_12),
                contentDescription = null
            )
            Text(
                text = name.value,
                fontFamily = pretendardFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Red500,
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
        color = Grey900,
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
private fun SettingButton(onClick: () -> Unit) {
    RedBigRoundButton28(
        onClick = onClick,
        text = "설정 완료"
    )
}
