package org.heet.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.R
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun RoundInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = White700,
            fontSize = 15.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(23.dp), color = White100)
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun BigRoundButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(Red200)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = pretendardFamily
        )
    }
}

@Composable
fun SmallRoundButton(
    modifier: Modifier = Modifier,
    text: String,
    isCheck: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(38.dp),
        onClick = onClick,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, Red300),
        colors = if (isCheck.value) {
            ButtonDefaults.buttonColors(Red200)
        } else {
            ButtonDefaults.buttonColors(Color.White)
        }
    ) {
        Text(
            text = text,
            fontFamily = pretendardFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = if (isCheck.value) {
                Color.White
            } else {
                Red200
            }
        )
    }
}

@Composable
fun FlatInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String = "",
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit = { valueState.value = it }
) {
    BasicTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = onValueChange,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        textStyle = TextStyle.Default.copy(
            color = Grey300,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        decorationBox = { innerTextField ->
            Box {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700
                    )
                }
                innerTextField()
            }
        },
        singleLine = isSingleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}

@Composable
fun GreyValidateText(text: String, isValidate: Boolean = true) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_black_check)
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
            color = White800
        )
    }
}

@Composable
fun BlackValidateText(text: String, isValidate: Boolean = true) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_black_check)
        else painterResource(id = R.drawable.ic_grey_check)
        Image(
            painter = check,
            contentDescription = null,
            modifier = Modifier.padding(end = 7.dp)
        )
        Text(
            text = text,
            fontFamily = pretendardFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Composable
fun Terms(text: String, isChecked: Boolean = false) {
    val check = if (isChecked) painterResource(id = R.drawable.ic_black_check)
    else painterResource(id = R.drawable.ic_grey_check)
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Image(
                painter = check,
                contentDescription = null,
                modifier = Modifier.clickable {
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text,
                fontFamily = pretendardFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Grey100
            )
        }
        Text(
            text = "자세히",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 4.dp)
                .clickable { },
            fontFamily = pretendardFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Grey100,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun Back(back: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "back",
        modifier = Modifier.clickable { back() }
    )
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily
    )
}

@Composable
fun EmptyText() {
    Text(
        text = "빈곳",
        color = Color.White,
        fontSize = 17.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun Next(
    timer: () -> Unit = {},
    move: () -> Unit
) {
    Text(
        text = "다음",
        modifier = Modifier.clickable {
            timer()
            move()
        },
        color = Red200,
        fontSize = 17.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun Finish(
    modifier: Modifier = Modifier,
    move: () -> Unit
) {
    Text(
        text = "완료",
        modifier = modifier.clickable {
            move()
        },
        color = Red200,
        fontSize = 17.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun PretendardDescription(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = White900
    )
}

@Composable
fun Description(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = White700,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun ReSendBtn(
    isCheck: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Text(
        text = "재전송하기",
        modifier = Modifier
            .clickable {
                if (isCheck.value) {
                    onClick()
                }
            },
        color = Grey700,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun Hide(
    isHide: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val passwordImage = if (isHide) {
        painterResource(id = R.drawable.ic_hide_pwd)
    } else {
        painterResource(id = R.drawable.ic_show_pwd)
    }
    Image(
        painter = passwordImage,
        contentDescription = null,
        modifier = modifier
            .size(44.dp)
            .clickable { onClick() }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PwdField(
    pwd: MutableState<String>,
    pwdValidState: Boolean,
    isHide: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?,
    isNumber: MutableState<Boolean>,
    isAlphabet: MutableState<Boolean>,
    isSpecialChar: MutableState<Boolean>,
    isValidateLength: MutableState<Boolean>,
    isValidatePwd: MutableState<Boolean>,
    checkPwd: MutableState<Boolean>
) {
    val containValidation = listOf(isNumber.value, isAlphabet.value, isSpecialChar.value)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        isNumber.value = pwd.value.contains("[0-9]".toRegex())
        isAlphabet.value = pwd.value.contains("[a-zA-Z]".toRegex())
        isSpecialChar.value = pwd.value.contains("[^가-힣\\d\\w]".toRegex())
        isValidateLength.value = pwd.value.length in 8..32
        isValidatePwd.value = isValidateLength.value && containValidation.count { it } >= 2
        checkPwd.value = isValidatePwd.value

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SecondPwdField(
    pwd: MutableState<String>,
    secondPwd: MutableState<String>,
    secondPwdValidState: Boolean,
    isHide: MutableState<Boolean>,
    isSame: MutableState<Boolean>,
    keyboardController: SoftwareKeyboardController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp)
    ) {
        if (pwd.value.isNotEmpty()) {
            isSame.value = pwd.value == secondPwd.value
        }

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
            painterResource(id = R.drawable.ic_hide_pwd)
        } else {
            painterResource(id = R.drawable.ic_show_pwd)
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

@Composable
fun ValidateText(text: String, isValidate: Boolean) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_black_check)
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
            color = White800
        )
    }
}

@Composable
fun RequestBtn(isCheck: MutableState<Boolean>, text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(38.dp),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, Red300),
        colors = if (isCheck.value) {
            ButtonDefaults.buttonColors(Red200)
        } else {
            ButtonDefaults.buttonColors(Color.White)
        }
    ) {
        Text(
            text = text,
            color = if (isCheck.value) {
                Color.White
            } else {
                Red200
            },
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
    }
}

@Composable
fun RegularFlatInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String = "",
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    isDuplicate: MutableState<Boolean>,
    onValueChange: (String) -> Unit = {
        valueState.value = it
        isDuplicate.value = true
    }
) {
    BasicTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = onValueChange,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        textStyle = TextStyle.Default.copy(
            color = White300,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        ),
        decorationBox = { innerTextField ->
            Box {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700
                    )
                }
                innerTextField()
            }
        },
        singleLine = isSingleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}
