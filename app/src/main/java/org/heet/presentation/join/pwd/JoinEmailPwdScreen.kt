package org.heet.presentation.join.pwd

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
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JoinEmailPwdScreen(navController: NavController) {
    val joinEmailPwdStateHolder = remember { JoinEmailPwdStateHolder() }
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
                Title("회원 가입")
                if (joinEmailPwdStateHolder.isSame.value) {
                    Next { navController.navigate(HeetScreens.JoinIdScreen.name) }
                } else {
                    EmptyText()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 61.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "이메일",
                    color = Grey500,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "jenny0810@naver.com",
                    color = White500,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Column(modifier = Modifier.padding(top = 36.dp)) {
                Text(
                    text = "새 비밀번호를 입력하세요*",
                    fontFamily = pretendardFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey1200
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    PwdField(
                        pwd = joinEmailPwdStateHolder.pwd,
                        pwdValidState = joinEmailPwdStateHolder.pwd.value.trim().isNotEmpty(),
                        isHide = joinEmailPwdStateHolder.isHide,
                        keyboardController = keyboardController,
                        isNumber = joinEmailPwdStateHolder.isNumber,
                        isAlphabet = joinEmailPwdStateHolder.isAlphabet,
                        isSpecialChar = joinEmailPwdStateHolder.isSpecialChar,
                        isValidateLength = joinEmailPwdStateHolder.isValidateLength,
                        isValidatePwd = joinEmailPwdStateHolder.isValidatePwd,
                        checkPwd = joinEmailPwdStateHolder.checkPwd
                    )
                    Hide(
                        isHide = joinEmailPwdStateHolder.isHide.value,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        joinEmailPwdStateHolder.isHide.value = !joinEmailPwdStateHolder.isHide.value
                    }
                }
                Divider(
                    Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = Grey1000
                )
            }
            if (!joinEmailPwdStateHolder.checkPwd.value) {
                Column(modifier = Modifier.padding(12.dp)) {
                    ValidateText("숫자", joinEmailPwdStateHolder.isNumber.value)
                    ValidateText("영문", joinEmailPwdStateHolder.isAlphabet.value)
                    ValidateText("특수문자", joinEmailPwdStateHolder.isSpecialChar.value)
                    ValidateText("8자 이상", joinEmailPwdStateHolder.isValidateLength.value)
                }
            }

            Column(modifier = Modifier.padding(top = 18.dp)) {
                Text(
                    text = "비밀번호 재확인*",
                    fontFamily = pretendardFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey1200
                )
                SecondPwdField(
                    pwd = joinEmailPwdStateHolder.pwd,
                    secondPwd = joinEmailPwdStateHolder.secondPwd,
                    secondPwdValidState = joinEmailPwdStateHolder.secondPwd.value.trim()
                        .isNotEmpty(),
                    isHide = joinEmailPwdStateHolder.isHide,
                    isSame = joinEmailPwdStateHolder.isSame,
                    keyboardController = keyboardController
                )
                Divider(
                    Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = Grey1000
                )
                if (joinEmailPwdStateHolder.isSame.value) {
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
