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
    val resetPwdHolder = remember { ForgotPwdStateHolder() }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                Title("비밀번호 재설정")
                if (resetPwdHolder.isSame.value) {
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
                PretendardDescription("새 비밀번호를 입력하세요*")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp)
                ) {
                    PwdField(
                        pwd = resetPwdHolder.pwd,
                        pwdValidState = resetPwdHolder.pwd.value.trim().isNotEmpty(),
                        isHide = resetPwdHolder.isHide,
                        keyboardController = keyboardController,
                        isNumber = resetPwdHolder.isNumber,
                        isAlphabet = resetPwdHolder.isAlphabet,
                        isSpecialChar = resetPwdHolder.isSpecialChar,
                        isValidateLength = resetPwdHolder.isValidateLength,
                        isValidatePwd = resetPwdHolder.isValidatePwd,
                        checkPwd = resetPwdHolder.checkPwd
                    )
                    Hide(
                        isHide = resetPwdHolder.isHide.value,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        resetPwdHolder.isHide.value = !resetPwdHolder.isHide.value
                    }
                }
                Divider(
                    Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = Grey1000
                )
                if (!resetPwdHolder.checkPwd.value) {
                    if (resetPwdHolder.pwd.value.isNotEmpty()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            with(resetPwdHolder) {
                                ValidateText("숫자", isNumber.value)
                                ValidateText("영문", isAlphabet.value)
                                ValidateText("특수문자", isSpecialChar.value)
                                ValidateText("8자 이상", isValidateLength.value)
                            }
                        }
                    }
                } else {
                    Column(modifier = Modifier.padding(top = 18.dp)) {
                        PretendardDescription("비밀번호 재확인*")
                        SecondPwdField(
                            pwd = resetPwdHolder.pwd,
                            secondPwd = resetPwdHolder.secondPwd,
                            secondPwdValidState = resetPwdHolder.secondPwd.value.trim()
                                .isNotEmpty(),
                            isHide = resetPwdHolder.isHide,
                            isSame = resetPwdHolder.isSame,
                            keyboardController = keyboardController
                        )
                        Divider(
                            Modifier
                                .padding(top = 13.dp)
                                .fillMaxWidth()
                                .height(3.dp),
                            color = Grey1000
                        )
                        if (resetPwdHolder.isSame.value) {
                            Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_black_check),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 7.dp)
                                )
                                Text(
                                    text = "일치합니다.",
                                    color = Grey200,
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
