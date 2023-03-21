package org.heet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.heet.R
import org.heet.ui.theme.Black1000
import org.heet.ui.theme.Red500
import org.heet.util.pretendardFamily

@Composable
fun Back(modifier: Modifier = Modifier, back: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_back_black_30),
        contentDescription = "back",
        modifier = modifier.clickable { back() }
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = Black1000,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = pretendardFamily
    )
}

@Composable
fun Next(
    onClick: () -> Unit
) {
    Text(
        text = "다음",
        modifier = Modifier.clickable {
            onClick()
        },
        color = Red500,
        fontSize = 17.sp,
        fontWeight = FontWeight.Black
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
fun Finish(
    modifier: Modifier = Modifier
) {
    Text(
        text = "완료",
        modifier = modifier,
        color = Red500,
        fontSize = 17.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun Cancel(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_cancel_black_30),
        contentDescription = "cancel",
        modifier = modifier
    )
}
