package org.heet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.ui.theme.Grey700
import org.heet.ui.theme.Red500
import org.heet.ui.theme.Red600
import org.heet.util.pretendardFamily

@Composable
fun ReSendBtn(
    isCorrectCode: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = "재전송하기",
        modifier = Modifier
            .clickable {
                if (!isCorrectCode) {
                    onClick()
                }
            },
        color = Grey700,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun RedSmallRoundButton22(
    modifier: Modifier = Modifier,
    text: String,
    isCheck: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(38.dp),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, Red600),
        colors = if (isCheck) {
            ButtonDefaults.buttonColors(Red500)
        } else {
            ButtonDefaults.buttonColors(Color.White)
        }
    ) {
        Text(
            text = text,
            color = if (isCheck) {
                Color.White
            } else {
                Red500
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
    }
}

@Composable
fun RedBigRoundButton28(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color = Red500,
    textColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = pretendardFamily
        )
    }
}
