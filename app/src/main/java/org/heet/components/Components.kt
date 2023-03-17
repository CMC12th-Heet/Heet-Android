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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
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
    color: Color = White700,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPwd: Boolean = false,
    onStateChange: () -> Unit = {}
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
            fontWeight = FontWeight.Normal
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(23.dp), color = White50)
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
            color = Grey550,
            fontSize = fontSize,
            fontFamily = pretendardFamily,
            fontWeight = fontWeight
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = isSingleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                        color = White450
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun GreyValidateText(text: String, isValidate: Boolean = true) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_check_black_20)
        else painterResource(id = R.drawable.ic_check_grey_20)
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
            color = White750
        )
    }
}

@Composable
fun BlackValidateText(text: String, isValidate: Boolean = true) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_check_black_20)
        else painterResource(id = R.drawable.ic_check_grey_20)
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
    val check = if (isChecked) painterResource(id = R.drawable.ic_check_black_20)
    else painterResource(id = R.drawable.ic_check_grey_20)
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
                color = White600
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
            color = White600,
            textDecoration = TextDecoration.Underline
        )
    }
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
        color = Red500,
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
fun Hide(
    isHide: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val passwordImage = if (isHide) {
        painterResource(id = R.drawable.ic_eye_close_grey_44)
    } else {
        painterResource(id = R.drawable.ic_eye_open_grey_44)
    }
    Image(
        painter = passwordImage,
        contentDescription = null,
        modifier = modifier
            .size(44.dp)
            .clickable { onClick() }
    )
}

@Composable
fun ValidateText(text: String, isValidate: Boolean) {
    Row(modifier = Modifier.padding(bottom = 6.dp)) {
        val check = if (isValidate) painterResource(id = R.drawable.ic_check_black_20)
        else painterResource(id = R.drawable.ic_check_grey_20)
        Image(
            painter = check,
            contentDescription = null,
            modifier = Modifier.padding(end = 7.dp)
        )
        Text(
            text = text,
            color = White750,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
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
        border = BorderStroke(1.dp, Red600),
        colors = if (isCheck.value) {
            ButtonDefaults.buttonColors(Red500)
        } else {
            ButtonDefaults.buttonColors(Color.White)
        }
    ) {
        Text(
            text = text,
            color = if (isCheck.value) {
                Color.White
            } else {
                Red500
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
    onValueChange: (String) -> Unit = {
        valueState.value = it
    }
) {
    BasicTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = onValueChange,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        textStyle = TextStyle.Default.copy(
            color = White250,
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

@Composable
fun DotDivider() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(0.3.dp)
    ) {
        drawLine(
            color = White400,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}
