package org.heet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Grey650
import org.heet.util.pretendardFamily

@Composable
fun WithdrawDialog(showDialog: MutableState<Boolean>, onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 31.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = "탈퇴 후 계정 복구는 불가합니다.\n정말로 탈퇴하시겠습니까?",
            color = Color.Black,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
        Spacer(modifier = Modifier.height(34.dp))
        RedBigRoundButton28(onClick = {
            onClick()
            showDialog.value = false
        }, text = "탈퇴")
        Spacer(modifier = Modifier.height(13.dp))
        RedBigRoundButton28(
            onClick = {
                showDialog.value = false
            },
            text = "취소",
            color = Color.White,
            textColor = Grey650
        )
        Spacer(modifier = Modifier.height(41.dp))
    }
}

@Composable
fun CancelWriteDialog(showDialog: MutableState<Boolean>, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(37.dp))
        Text(
            text = "작성 중인 내용들이 있어요!\n그래도 나가시겠습니까?",
            color = Grey400,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
        Spacer(modifier = Modifier.height(27.dp))
        RedBigRoundButton28(
            onClick = {
                onClick()
                showDialog.value = false
            },
            modifier = Modifier.padding(horizontal = 93.dp),
            text = "확인"
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "취소",
            color = Grey400,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = pretendardFamily,
            modifier = Modifier.clickable {
                showDialog.value = false
            }
        )
        Spacer(modifier = Modifier.height(21.dp))
    }
}
