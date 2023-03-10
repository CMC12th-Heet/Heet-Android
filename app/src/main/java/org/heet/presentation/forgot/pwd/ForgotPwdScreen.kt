package org.heet.presentation.forgot.pwd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.AuthScreen
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPwdScreen(
    navController: NavController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pwd = remember {
        mutableStateOf("")
    }
    val secondPwd = remember {
        mutableStateOf("")
    }
    val isNumber = remember {
        mutableStateOf(false)
    }
    val isAlphabet = remember {
        mutableStateOf(false)
    }
    val isSpecialChar = remember {
        mutableStateOf(false)
    }
    val isValidateLength = remember {
        mutableStateOf(false)
    }
    val isValidatePwd = remember {
        mutableStateOf(false)
    }
    val checkPwd = remember {
        mutableStateOf(false)
    }
    val isHide = remember {
        mutableStateOf(true)
    }
    val isSame = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title("???????????? ?????????")
                if (isSame.value) {
                    Finish(
                        move = {
                            navController.navigate(AuthScreen.Login.route) {
                                popUpTo(0)
                            }
                        }
                    )
                } else {
                    EmptyText()
                }
            }
            Column(modifier = Modifier.padding(top = 153.dp)) {
                PretendardDescription("??? ??????????????? ???????????????*")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp)
                ) {
                    PwdField(
                        pwd = pwd,
                        pwdValidState = pwd.value.trim().isNotEmpty(),
                        isHide = isHide,
                        keyboardController = keyboardController,
                        isNumber = isNumber,
                        isAlphabet = isAlphabet,
                        isSpecialChar = isSpecialChar,
                        isValidateLength = isValidateLength,
                        isValidatePwd = isValidatePwd,
                        checkPwd = checkPwd
                    )
                    Hide(
                        isHide = isHide.value,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        isHide.value = !isHide.value
                    }
                }
                Divider(
                    Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = White350
                )
                if (!checkPwd.value) {
                    if (pwd.value.isNotEmpty()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            ValidateText("??????", isNumber.value)
                            ValidateText("??????", isAlphabet.value)
                            ValidateText("????????????", isSpecialChar.value)
                            ValidateText("8??? ??????", isValidateLength.value)
                        }
                    }
                } else {
                    Column(modifier = Modifier.padding(top = 18.dp)) {
                        PretendardDescription("???????????? ?????????*")
                        SecondPwdField(
                            pwd = pwd,
                            secondPwd = secondPwd,
                            secondPwdValidState = secondPwd.value.trim()
                                .isNotEmpty(),
                            isHide = isHide,
                            isSame = isSame,
                            keyboardController = keyboardController
                        )
                        Divider(
                            Modifier
                                .padding(top = 13.dp)
                                .fillMaxWidth()
                                .height(3.dp),
                            color = White350
                        )
                        if (isSame.value) {
                            Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_check_black_20),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 7.dp)
                                )
                                Text(
                                    text = "???????????????.",
                                    color = White750,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = pretendardFamily
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
