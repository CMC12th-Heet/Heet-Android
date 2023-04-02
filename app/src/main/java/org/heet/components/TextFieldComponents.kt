package org.heet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.R
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@Composable
fun GreyRoundTextField23(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    placeholder: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPwd: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onStateChange: () -> Unit = {},
) {
    RoundInputField(
        modifier = modifier,
        valueState = value,
        placeholder = placeholder,
        enabled = true,
        isSingleLine = true,
        onAction = onAction,
        isPwd = isPwd,
        imeAction = imeAction,
        onStateChange = onStateChange,
    )
}

@Composable
fun FlatTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String = "",
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit = { valueState.value = it },
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = Grey200,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Box {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700,
                    )
                }
                innerTextField()
            }
        },
    )
}

@Composable
fun PostFlatTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String = "",
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit = { valueState.value = it },
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = Grey1200,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = pretendardFamily,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Box {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Grey1100,
                    )
                }
                innerTextField()
            }
        },
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
    checkPwd: MutableState<Boolean>,
) {
    val containValidation = listOf(isNumber.value, isAlphabet.value, isSpecialChar.value)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp),
    ) {
        isNumber.value = pwd.value.contains("[0-9]".toRegex())
        isAlphabet.value = pwd.value.contains("[a-zA-Z]".toRegex())
        isSpecialChar.value = pwd.value.contains("[^가-힣\\d\\w]".toRegex())
        isValidateLength.value = pwd.value.length in 8..32
        isValidatePwd.value = isValidateLength.value && containValidation.count { it } >= 2
        checkPwd.value = isValidatePwd.value

        FlatTextField(
            modifier = Modifier
                .padding(end = 45.dp)
                .align(Alignment.CenterStart),
            valueState = pwd,
            placeholder = "숫자/영문/특수문자 중 두가지 이상, 8자~32자",
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Email,
            onAction = KeyboardActions {
                if (!pwdValidState) return@KeyboardActions
                keyboardController?.hide()
            },
            isPassword = isHide.value,
        )
        val passwordImage = if (isHide.value) {
            painterResource(id = R.drawable.ic_eye_close)
        } else {
            painterResource(id = R.drawable.ic_eye_open)
        }
        Image(
            painter = passwordImage,
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.CenterEnd)
                .clickable { isHide.value = !isHide.value },
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
    keyboardController: SoftwareKeyboardController?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp),
    ) {
        if (pwd.value.isNotEmpty()) {
            isSame.value = pwd.value == secondPwd.value
        }

        FlatTextField(
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
            isPassword = isHide.value,
        )
        val passwordImage = if (isHide.value) {
            painterResource(id = R.drawable.ic_eye_close)
        } else {
            painterResource(id = R.drawable.ic_eye_open)
        }
        Image(
            painter = passwordImage,
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.CenterEnd)
                .clickable { isHide.value = !isHide.value },
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
    onValueChange: (String) -> Unit = {
        valueState.value = it
    },
) {
    BasicTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = onValueChange,
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        textStyle = TextStyle.Default.copy(
            color = White250,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        ),
        decorationBox = { innerTextField ->
            Box {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700,
                    )
                }
                innerTextField()
            }
        },
        singleLine = isSingleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
    )
}

@Composable
fun NormalInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    fontSize: TextUnit = 17.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = Grey550,
            fontSize = fontSize,
            fontFamily = pretendardFamily,
            fontWeight = fontWeight,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                        color = White450,
                    )
                }
                innerTextField()
            }
        },
    )
}

@Composable
fun RoundInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    color: Color = White700,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPwd: Boolean = false,
    onStateChange: () -> Unit = {},
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            onStateChange()
        },
        visualTransformation = if (isPwd) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = color,
            fontSize = 15.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(23.dp), color = White50)
                    .padding(horizontal = 20.dp, vertical = 14.dp),
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = White700,
                    )
                }
                innerTextField()
            }
        },
    )
}
