package org.heet.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
            color = Grey300,
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
                    .background(shape = RoundedCornerShape(23.dp), color = Grey100)
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Grey300
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
        colors = ButtonDefaults.buttonColors(Red400)
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
        border = BorderStroke(1.dp, Red200),
        colors = if (isCheck.value) {
            ButtonDefaults.buttonColors(Red400)
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
                Red400
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
            color = Grey900,
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
                        color = Grey300
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
fun GreyFixedTextField(title: String, content: String) {
    Text(
        text = title,
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Grey500
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = content,
        fontFamily = pretendardFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = White500
    )
    Spacer(modifier = Modifier.height(12.dp))
    Divider(
        Modifier
            .fillMaxWidth()
            .height(3.dp),
        color = White400
    )
}

@Composable
fun ScreenTitle(
    title: String,
    modifier: Modifier = Modifier,
    goBack: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = title,
            modifier = Modifier.clickable {
                goBack()
            }
        )
        Text(
            text = title,
            fontFamily = pretendardFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = modifier
        )
    }
}

@Composable
fun NextImage(
    navController: NavController,
    modifier: Modifier,
    destination: String,
    timerReset: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(top = 98.dp)
            .clickable {
                timerReset()
                navController.navigate(destination)
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "다음",
            modifier = Modifier.padding(end = 5.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = Red400
        )
        Image(
            painter = painterResource(id = R.drawable.ic_red_next),
            contentDescription = null
        )
    }
}

@Composable
fun RedDescription(description: String) {
    Text(
        text = description,
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Red200
    )
}

@Composable
fun GreyValidateText(text: String, isValidate: Boolean = true) {
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

@Composable
fun BlackValidateText(text: String, isValidate: Boolean = true) {
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
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Composable
fun Terms(text: String, isChecked: Boolean = false) {
    val check = if (isChecked) painterResource(id = R.drawable.ic_green_check)
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
                color = White900
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
            color = White900,
            textDecoration = TextDecoration.Underline
        )
    }
}
