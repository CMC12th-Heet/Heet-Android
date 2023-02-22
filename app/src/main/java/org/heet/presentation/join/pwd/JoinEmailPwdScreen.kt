package org.heet.presentation.join.pwd

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
fun JoinEmailPwdScreen(navController: NavController) {
    val joinPassword = remember {
        mutableStateOf("")
    }
    val jointPasswordValidState = remember(joinPassword.value) {
        joinPassword.value.trim().isNotEmpty()
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
    val checkPwd = remember {
        mutableStateOf("")
    }
    val checkPwdValidState = remember(checkPwd.value) {
        checkPwd.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            ScreenTitle(
                title = "회원 가입",
                modifier = Modifier.padding(start = 118.dp)
            ) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(48.dp))
            GreyFixedTextField(title = "이메일*", content = "jenny0810@naver.com")
            Spacer(modifier = Modifier.height(51.dp))
            RedDescription(description = "비밀번호를 설정하세요*")
            InputPasswordField(
                joinPassword = joinPassword,
                jointPasswordValidState = jointPasswordValidState,
                isHide = isHide,
                keyboardController = keyboardController,
                isValidate = isValidate,
                containNumber = containNumber,
                containAlphabet = containAlphabet,
                containSpecialCharacters = containSpecialCharacters,
                validateLength = validateLength,
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
            }
            Spacer(modifier = Modifier.height(18.dp))
            RedDescription(description = "비밀번호 재확인*")
            CheckPasswordField(
                checkPassword = checkPwd,
                joinPassword = joinPassword,
                checkPasswordValidState = checkPwdValidState,
                isHide = isHide,
                keyboardController = keyboardController
            )
            NextImage(
                navController,
                Modifier.align(Alignment.End),
                HeetScreens.JoinIdScreen.name
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CheckPasswordField(
    checkPassword: MutableState<String>,
    joinPassword: MutableState<String>,
    checkPasswordValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            valueState = checkPassword,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!checkPasswordValidState) return@KeyboardActions
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
    if (joinPassword.value == checkPassword.value && joinPassword.value.isNotEmpty()) {
        Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_green_check),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "일치합니다.",
                fontFamily = pretendardFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Grey200
            )
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
            color = Grey200
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputPasswordField(
    joinPassword: MutableState<String>,
    jointPasswordValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?,
    isValidate: MutableState<Boolean>,
    containNumber: MutableState<Boolean>,
    containAlphabet: MutableState<Boolean>,
    containSpecialCharacters: MutableState<Boolean>,
    validateLength: MutableState<Boolean>,
    checkPassword: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        containNumber.value = joinPassword.value.contains("[0-9]".toRegex())
        containAlphabet.value = joinPassword.value.contains("[a-zA-Z]".toRegex())
        containSpecialCharacters.value = joinPassword.value.contains("[^가-힣\\d\\w]".toRegex())
        validateLength.value = joinPassword.value.length in 8..32
        val containValidation =
            listOf(containNumber.value, containAlphabet.value, containSpecialCharacters.value)

        isValidate.value = validateLength.value && containValidation.count { it } >= 2
        LaunchedEffect(isValidate.value) {
            checkPassword.value = isValidate.value
        }

        FlatInputField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            placeholder = "숫자/영문/특수문자 중 두가지 이상, 8자~32자",
            valueState = joinPassword,
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!jointPasswordValidState) return@KeyboardActions
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
