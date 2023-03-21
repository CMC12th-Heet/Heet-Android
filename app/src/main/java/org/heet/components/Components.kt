package org.heet.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.R
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

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
fun Terms(text: String, isChecked: Boolean = false, goToDetail: () -> Unit) {
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
        if (text != "[필수] 만 14세 이상입니다.") {
            Text(
                text = "자세히",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 4.dp)
                    .clickable {
                        goToDetail()
                    },
                fontFamily = pretendardFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = White600,
                textDecoration = TextDecoration.Underline
            )
        }
    }
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
fun ShareTip(
    text: String,
    placeHolder: String,
    modifier: Modifier = Modifier,
    dp: Dp = 34.dp,
    enabled: Boolean = true,
    shareTip: MutableState<String>? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            color = White700
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(
                    horizontal = dp,
                    vertical = 7.dp
                ),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily
            )
        }
        Spacer(modifier = Modifier.width(7.dp))
        if (shareTip == null) {
            Text(
                text = placeHolder,
                color = Grey1200,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = pretendardFamily
            )
        } else {
            PostFlatTextField(
                valueState = shareTip,
                modifier = Modifier.padding(end = 20.dp),
                enabled = enabled,
                isSingleLine = true,
                placeholder = placeHolder
            )
        }
    }
    Spacer(modifier = Modifier.height(7.dp))
}
