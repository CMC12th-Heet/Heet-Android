package org.heet.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.ui.theme.Grey200
import org.heet.ui.theme.Red200
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
        textStyle = TextStyle.Default.copy(
            color = Grey200,
            fontSize = 15.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Red200,
                        shape = RoundedCornerShape(23.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                if (valueState.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontFamily = pretendardFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Grey200
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
fun BigRoundButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(52.dp),
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(Red200)
    ) {
        Text(
            text = text,
            fontFamily = pretendardFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
    }
}
