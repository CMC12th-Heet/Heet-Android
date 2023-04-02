package org.heet.presentation.home.mypage.modify

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.Graph
import org.heet.core.navigation.navscreen.MyPageScreen
import org.heet.data.datasource.LocalHometownDataSource
import org.heet.data.model.response.ResponseGetMyPage
import org.heet.presentation.signup.residence.*
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun ModifyProfileScreen(
    navController: NavController,
    modifyProfileViewModel: ModifyProfileViewModel = hiltViewModel(),
) {
    modifyProfileViewModel.getMyPage()
    val profile = modifyProfileViewModel.profile.collectAsState().value
    val town = remember { mutableStateOf("") }
    val id = remember { mutableStateOf("") }
    val introduce = remember { mutableStateOf("") }

    val cityName = remember { mutableStateOf("") }
    val wardName = remember { mutableStateOf("") }
    val checkCity = remember { mutableStateOf(false) }
    val checkWard = remember { mutableStateOf(false) }

    val showDialog = remember { mutableStateOf(false) }
    val showTown = remember { mutableStateOf(false) }

    if (showDialog.value) {
        Withdraw(showDialog = showDialog, modifyProfileViewModel = modifyProfileViewModel)
    }

    LaunchedEffect(
        modifyProfileViewModel.withdrawSuccess.collectAsState().value,
        modifyProfileViewModel.modifySuccess.collectAsState().value,
    ) {
        if (modifyProfileViewModel.withdrawSuccess.value) {
            navController.navigate(Graph.AUTHENTICATION) {
                popUpTo(0)
            }
        }
        if (modifyProfileViewModel.modifySuccess.value) {
            navController.popBackStack()
        }
    }

    if (showTown.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Back { showTown.value = false }
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
                            painterResource(id = R.drawable.ic_arrow_down_grey)
                        } else {
                            painterResource(id = R.drawable.ic_arrow_next_22)
                        }
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CityItem(
                                city.cityKr,
                                image,
                                expanded,
                                checkWard,
                                checkCity,
                                cityName,
                            )
                        }
                        if (expanded.value) {
                            var where = listOf<String>()
                            when (city.cityEng) {
                                "seoul" -> {
                                    modifyProfileViewModel.getSeoulCity(city.cityEng)
                                    where = modifyProfileViewModel.seoul.collectAsState().value
                                }
                                "gyeonggi" -> {
                                    modifyProfileViewModel.getGyeonggiCity(city.cityEng)
                                    where = modifyProfileViewModel.gyeonggi.collectAsState().value
                                }
                                "incheon" -> {
                                    modifyProfileViewModel.getIncheonCity(city.cityEng)
                                    where = modifyProfileViewModel.incheon.collectAsState().value
                                }
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            LazyRow(modifier = Modifier.padding(start = 30.dp)) {
                                items(
                                    where,
                                ) { ward ->
                                    WardItem(
                                        updateResidence = { town.value = "${city.cityKr} $ward" },
                                        ward,
                                        city.cityKr,
                                        checkWard,
                                        wardName,
                                        cityName,
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
                    showTown.value = false
                }
            }
        }
    } else if (profile != null && !showTown.value) {
        Column(
            modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TopBar(navController, town, id, introduce)
                Spacer(modifier = Modifier.height(40.dp))
                ProfileImage()
                Spacer(modifier = Modifier.height(30.dp))
                Location(profile, showTown, town)
                Spacer(modifier = Modifier.height(3.dp))
                DotDivider()
                Spacer(modifier = Modifier.height(25.dp))
                IdTextField(id)
                Spacer(modifier = Modifier.height(63.dp))
                IntroduceTextField(introduce)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                LogoutBtn(modifyProfileViewModel, navController)
                Spacer(modifier = Modifier.width(65.dp))
                Divider(modifier = Modifier.size(width = 1.dp, height = 18.dp))
                Spacer(modifier = Modifier.width(65.dp))
                WithdrawBtn(showDialog)
            }
            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}

@Composable
private fun IntroduceTextField(introduce: MutableState<String>) {
    Text(
        text = "소개글 작성",
        modifier = Modifier
            .padding(start = 13.dp)
            .fillMaxWidth(),
        color = Grey50,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily,
    )
    Spacer(modifier = Modifier.height(7.dp))
    RoundInputField(
        modifier = Modifier.height(85.dp),
        valueState = introduce,
        placeholder = "소개글",
        enabled = true,
        isSingleLine = true,
        color = Grey150,
    )
    Spacer(modifier = Modifier.height(7.dp))
    Text(
        text = "최대 30 자",
        modifier = Modifier.padding(start = 10.dp)
            .fillMaxWidth(),
        color = Grey50,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily,
    )
}

@Composable
private fun IdTextField(id: MutableState<String>) {
    Text(
        text = "아이디",
        modifier = Modifier.padding(start = 15.dp)
            .fillMaxWidth(),
        color = Grey50,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily,
    )
    Spacer(modifier = Modifier.height(7.dp))
    RoundInputField(
        valueState = id,
        placeholder = "아이디",
        enabled = true,
        isSingleLine = true,
        color = Grey150,
    )
}

@Composable
private fun Location(
    profile: ResponseGetMyPage,
    showTown: MutableState<Boolean>,
    town: MutableState<String>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_red_15),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = if (town.value == "") profile.town else town.value,
                color = Red500,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily,
            )
        }
        Text(
            text = "지역 변경하기",
            modifier = Modifier.align(Alignment.Bottom).clickable {
                showTown.value = true
            },
            color = Grey50,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
private fun ProfileImage() {
    Box {
        Surface(
            modifier = Modifier.size(71.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, White250),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_profile_modify),
                contentDescription = null,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd),
        )
    }
}

@Composable
private fun TopBar(
    navController: NavController,
    town: MutableState<String>,
    id: MutableState<String>,
    introduce: MutableState<String>,
    modifyProfileViewModel: ModifyProfileViewModel = hiltViewModel(),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Back { navController.popBackStack() }
        Title(title = "프로필 수정")
        Finish(
            modifier = Modifier.clickable {
                modifyProfileViewModel.modifyProfile(id.value, town.value, introduce.value)
            },
        )
    }
}

@Composable
private fun WithdrawBtn(showDialog: MutableState<Boolean>) {
    Text(
        text = "회원탈퇴",
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily,
        modifier = Modifier.clickable {
            showDialog.value = true
        },
    )
}

@Composable
private fun LogoutBtn(
    modifyProfileViewModel: ModifyProfileViewModel,
    navController: NavController,
) {
    Text(
        text = "로그아웃",
        modifier = Modifier.clickable {
            modifyProfileViewModel.deleteUserPreferences()
            modifyProfileViewModel.deleteVerify()
            navController.navigate(MyPageScreen.Login.route)
        },
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily,
    )
}

@Composable
fun Withdraw(showDialog: MutableState<Boolean>, modifyProfileViewModel: ModifyProfileViewModel) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
        ) {
            WithdrawDialog(showDialog = showDialog) {
                modifyProfileViewModel.withdraw()
            }
        }
    }
}
