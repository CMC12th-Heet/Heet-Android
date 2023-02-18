package org.heet.presentation.join

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.ui.theme.Red400
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JoinIdScreen(navController: NavController) {
    val id = remember {
        mutableStateOf("")
    }
    val idValidState = remember(id.value) {
        id.value.trim().isNotEmpty()
    }
    val isDuplicate = remember {
        mutableStateOf(true)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            ScreenTitle(
                title = "회원 가입",
                modifier = Modifier.padding(start = 118.dp)
            ) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(48.dp))
            GreyFixedTextField(title = "이메일*", content = "jenny0810@naver.com")
            Spacer(modifier = Modifier.height(24.dp))
            GreyFixedTextField(title = "비밀번호*", content = "********")
            Spacer(modifier = Modifier.height(24.dp))
            RedDescription(description = "아이디를 설정하세요*")
            Spacer(modifier = Modifier.height(13.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                FlatInputField(
                    modifier = Modifier
                        .padding(start = 9.dp, end = 117.dp)
                        .align(Alignment.CenterStart),
                    valueState = id,
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {
                        if (!idValidState) return@KeyboardActions
                        keyboardController?.hide()
                    }
                )
                SmallRoundButton(
                    text = "중복 확인",
                    modifier = Modifier.align(Alignment.CenterEnd),
                    isCheck = isDuplicate
                ) {
                    isDuplicate.value = !isDuplicate.value
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.fillMaxWidth().height(3.dp), color = Red400)
            Spacer(modifier = Modifier.height(15.dp))
            if (isDuplicate.value) {
                Text(
                    text = "*이미 사용 중인 아이디입니다.",
                    fontFamily = pretendardFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Red400
                )
            } else {
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    GreyValidateText(text = "사용 가능합니다.")
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    BlackValidateText(text = "약관 전체 동의하기")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    Terms(text = "[필수] 개인정보 수징 및 이용 동의")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    Terms(text = "[필수] HEET 이용 약관 동의")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    Terms(text = "[필수]만 14세 이상입니다.")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Spacer(modifier = Modifier.width(12.dp))
                    Terms(text = "[필수]마케팅 활용 및 광고성 정보 수신 동의")
                }
            }
        }
        BigRoundButton(
            onClick = { },
            text = "회원 가입",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
        )
    }
}
