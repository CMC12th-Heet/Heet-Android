package org.heet.presentation.resetpwd

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun ResetPasswordScreen(
    navController: NavController
) {
    val resetPwdHolder = remember { ResetPwdStateHolder() }
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
                            navController.navigate(HeetScreens.LoginScreen.name) {
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
                        resetPwdHolder = resetPwdHolder
                    )
                    Hide(resetPwdHolder, modifier = Modifier.align(Alignment.CenterEnd))
                }
                Divider(
                    Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = Red200
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
                            resetPwdHolder = resetPwdHolder,
                            secondPwdValidState = resetPwdHolder.secondPwd.value.trim()
                                .isNotEmpty(),
                            isHide = resetPwdHolder.isHide,
                            keyboardController = keyboardController
                        )
                        Divider(
                            Modifier
                                .padding(top = 13.dp)
                                .fillMaxWidth()
                                .height(3.dp),
                            color = Red200
                        )
                        if (resetPwdHolder.isSame.value) {
                            Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_blue_check),
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

@Composable
private fun Hide(resetPwdHolder: ResetPwdStateHolder, modifier: Modifier) {
    val passwordImage = if (resetPwdHolder.isHide.value) {
        painterResource(id = R.drawable.ic_hide_password)
    } else {
        painterResource(id = R.drawable.ic_show_password)
    }
    Image(
        painter = passwordImage,
        contentDescription = null,
        modifier = modifier
            .size(44.dp)
            .clickable { resetPwdHolder.setIsHide() }
    )
}

@Composable
private fun ValidateText(text: String, isValidate: Boolean) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_blue_check)
        else painterResource(id = R.drawable.ic_grey_check)
        Image(
            painter = check,
            contentDescription = null,
            modifier = Modifier.padding(end = 7.dp)
        )
        Text(
            text = text,
            color = Grey200,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SecondPwdField(
    pwd: MutableState<String>,
    secondPwd: MutableState<String>,
    resetPwdHolder: ResetPwdStateHolder,
    secondPwdValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        resetPwdHolder.setIsSame(pwd.value == secondPwd.value)

        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            valueState = secondPwd,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!secondPwdValidState) return@KeyboardActions
                keyboardController?.hide()
            },
            isPassword = isHide.value
        )
        val passwordImage = if (isHide.value) {
            painterResource(id = R.drawable.ic_hide_password)
        } else {
            painterResource(id = R.drawable.ic_show_password)
        }
        Image(
            painter = passwordImage,
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.CenterEnd)
                .clickable { isHide.value = !isHide.value }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PwdField(
    pwd: MutableState<String>,
    pwdValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?,
    isNumber: MutableState<Boolean>,
    isAlphabet: MutableState<Boolean>,
    isSpecialChar: MutableState<Boolean>,
    isValidateLength: MutableState<Boolean>,
    resetPwdHolder: ResetPwdStateHolder
) {
    val containValidation = listOf(isNumber.value, isAlphabet.value, isSpecialChar.value)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        with(resetPwdHolder) {
            setContainNumber(pwd.value.contains("[0-9]".toRegex()))
            setContainAlphabet(pwd.value.contains("[a-zA-Z]".toRegex()))
            setContainSpecialCharacters(pwd.value.contains("[^가-힣\\d\\w]".toRegex()))
            setValidateLength(pwd.value.length in 8..32)
            setValidatePwd(isValidateLength.value && containValidation.count { it } >= 2)
            setCheckPassword(isValidatePwd.value)
        }

        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            placeholder = "숫자/영문/특수문자 중 두가지 이상, 8자~32자",
            valueState = pwd,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!pwdValidState) return@KeyboardActions
                keyboardController?.hide()
            },
            isPassword = isHide.value
        )
    }
}
