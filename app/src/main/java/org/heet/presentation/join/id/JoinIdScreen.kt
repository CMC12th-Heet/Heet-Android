package org.heet.presentation.join.id

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JoinIdScreen(navController: NavController) {
    val joinIdStateHolder = remember { JoinIdStateHolder() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title("회원 가입")
                EmptyText()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 61.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "이메일",
                    color = Grey500,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "jenny0810@naver.com",
                    color = White500,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "비밀번호",
                    color = Grey500,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
                Text(
                    text = "********",
                    color = White500,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily
                )
            }
            Column(modifier = Modifier.padding(top = 111.dp)) {
                Text(
                    text = "아이디를 설정하세요*",
                    fontFamily = pretendardFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey1200
                )
                Box(
                    modifier = Modifier.fillMaxWidth().padding(
                        top = 13.dp
                    )
                ) {
                    RegularFlatInputField(
                        modifier = Modifier
                            .padding(end = 117.dp)
                            .align(Alignment.CenterStart),
                        valueState = joinIdStateHolder.id,
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text,
                        onAction = KeyboardActions {
                            if (joinIdStateHolder.id.value.trim().isEmpty()
                            ) return@KeyboardActions
                            keyboardController?.hide()
                        },
                        isDuplicate = joinIdStateHolder.isDuplicate
                    )
                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (joinIdStateHolder.isDuplicate.value) {
                            RequestBtn(
                                isCheck = joinIdStateHolder.requestCheckDuplicate,
                                text = "중복 확인"
                            ) {
                                joinIdStateHolder.requestCheckDuplicate.value = true
                                joinIdStateHolder.isDuplicate.value =
                                    !joinIdStateHolder.isDuplicate.value
                            }
                        } else {
                            Spacer(modifier = Modifier.height(38.dp))
                        }
                    }
                }
            }

            Divider(
                Modifier.fillMaxWidth().padding(
                    top = 10.dp,
                    bottom = 15.dp
                ).height(3.dp),
                color = Grey300
            )
            if (joinIdStateHolder.isDuplicate.value) {
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
                val terms = listOf(
                    "[필수] 개인정보 수징 및 이용 동의",
                    "[필수] HEET 이용 약관 동의",
                    "[필수]만 14세 이상입니다.",
                    "[필수]마케팅 활용 및 광고성 정보 수신 동의"
                )
                LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
                    items(terms) {
                        Row {
                            Spacer(modifier = Modifier.width(12.dp))
                            Terms(text = it)
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
        BigRoundButton(
            onClick = { navController.navigate(HeetScreens.JoinFinish.name) },
            text = "회원 가입",
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.BottomCenter)
        )
    }
}
