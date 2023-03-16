package org.heet.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import org.heet.ui.theme.Grey200
import org.heet.ui.theme.White700
import org.heet.util.pretendardFamily

@Composable
fun GreyRoundTextField23(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    placeholder: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    isPwd: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onStateChange: () -> Unit = {}
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
        onStateChange = onStateChange
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
    onValueChange: (String) -> Unit = { valueState.value = it }
) {
    BasicTextField(
        value = valueState.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = TextStyle.Default.copy(
            color = Grey200,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
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
                        color = White700
                    )
                }
                innerTextField()
            }
        }
    )
}
