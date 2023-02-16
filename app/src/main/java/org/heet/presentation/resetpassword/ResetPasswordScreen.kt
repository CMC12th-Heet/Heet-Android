package org.heet.presentation.resetpassword

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.heet.R
import org.heet.components.FlatInputField
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ResetPasswordScreen(
    navController: NavController
) {
    val resetPassword = remember {
        mutableStateOf("")
    }
    val resetPasswordValidState = remember(resetPassword.value) {
        resetPassword.value.trim().isNotEmpty()
    }
    val checkPwd = remember {
        mutableStateOf("")
    }
    val checkPwdValidState = remember(checkPwd.value) {
        checkPwd.value.trim().isNotEmpty()
    }
    val isHide = remember {
        mutableStateOf(true)
    }
    val isValidate = remember {
        mutableStateOf(false)
    }
    val containNumber = remember {
        mutableStateOf(false)
    }
    val containAlphabet = remember {
        mutableStateOf(false)
    }
    val containSpecialCharacters = remember {
        mutableStateOf(false)
    }
    val validateLength = remember {
        mutableStateOf(false)
    }
    val checkPassword = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 14.dp)
        ) {
            ResetPasswordTitle(navController)
            Column(
                modifier = Modifier
                    .padding(top = 153.dp)
            ) {
                InputPasswordField(
                    resetPassword = resetPassword,
                    restPasswordValidState = resetPasswordValidState,
                    isHide = isHide,
                    keyboardController = keyboardController,
                    containNumber = containNumber,
                    containAlphabet = containAlphabet,
                    containSpecialCharacters = containSpecialCharacters,
                    validateLength = validateLength,
                    isValidate = isValidate,
                    checkPassword = checkPassword
                )
                if (!checkPassword.value) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        if (!isValidate.value) {
                            ValidateText("숫자", containNumber.value)
                            ValidateText("영문", containAlphabet.value)
                            ValidateText("특수문자", containSpecialCharacters.value)
                            ValidateText("8자 이상", validateLength.value)
                        } else {
                            ValidateText("사용 가능합니다.", isValidate.value)
                        }
                    }
                } else {
                    Column(modifier = Modifier.padding(top = 18.dp)) {
                        CheckPasswordField(
                            resetPassword = checkPwd,
                            restPasswordValidState = checkPwdValidState,
                            isHide = isHide,
                            keyboardController = keyboardController
                        )
                        Text(
                            text = "완료",
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(top = 113.dp, end = 19.dp)
                                .clickable {
                                    navController.navigate(HeetScreens.LoginScreen.name) {
                                        popUpTo(0)
                                    }
                                },
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Red400
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ValidateText(text: String, isValidate: Boolean) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_green_check)
        else painterResource(id = R.drawable.ic_grey_check)
        Image(
            painter = check,
            contentDescription = null,
            modifier = Modifier.padding(end = 7.dp)
        )
        Text(
            text = text,
            fontFamily = pretendardFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = Grey400
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CheckPasswordField(
    resetPassword: MutableState<String>,
    restPasswordValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?
) {
    Text(
        text = "비밀번호 재확인*",
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Red200
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            valueState = resetPassword,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!restPasswordValidState) return@KeyboardActions
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
    Divider(
        Modifier
            .padding(top = 13.dp)
            .fillMaxWidth()
            .height(3.dp),
        color = Red200
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputPasswordField(
    resetPassword: MutableState<String>,
    restPasswordValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?,
    isValidate: MutableState<Boolean>,
    containNumber: MutableState<Boolean>,
    containAlphabet: MutableState<Boolean>,
    containSpecialCharacters: MutableState<Boolean>,
    validateLength: MutableState<Boolean>,
    checkPassword: MutableState<Boolean>
) {
    Text(
        text = "새 비밀번호를 입력하세요*",
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Red200
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        containNumber.value = resetPassword.value.contains("[0-9]".toRegex())
        containAlphabet.value = resetPassword.value.contains("[a-zA-Z]".toRegex())
        containSpecialCharacters.value = resetPassword.value.contains("[^가-힣\\d\\w]".toRegex())
        validateLength.value = resetPassword.value.length in 8..32
        val containValidation =
            listOf(containNumber.value, containAlphabet.value, containSpecialCharacters.value)

        isValidate.value = validateLength.value && containValidation.count { it } >= 2
        LaunchedEffect(isValidate.value) {
            if (isValidate.value) {
                delay(1000L)
                checkPassword.value = true
            } else {
                checkPassword.value = false
            }
        }

        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            placeholder = "숫자/영문/특수문자 중 두가지 이상, 8자~32자",
            valueState = resetPassword,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!restPasswordValidState) return@KeyboardActions
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
    Divider(
        Modifier
            .padding(top = 13.dp)
            .fillMaxWidth()
            .height(3.dp),
        color = Red200
    )
}

@Composable
private fun ResetPasswordTitle(navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Text(
            text = "비밀번호 재설정",
            fontFamily = pretendardFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(start = 85.dp)
        )
    }
}
