package org.heet.presentation.findpwd

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.components.*
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordScreen(
    navController: NavController,
    findPwdViewModel: FindPwdViewModel = hiltViewModel()
) {
    val findPwdHolder = remember { FindPwdStateHolder() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val verificationTimer = remember {
        findPwdViewModel.timer
    }

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
                Title("비밀번호 찾기")
                if (findPwdHolder.requestCode.value) {
                    Next(
                        timer = { findPwdViewModel.timerReset() },
                        move = { navController.navigate(HeetScreens.ResetPwdScreen.name) }
                    )
                } else {
                    EmptyText()
                }
            }
            Column(modifier = Modifier.padding(top = 153.dp)) {
                PretendardDescription("이메일*")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FlatInputField(
                        modifier = Modifier.width(203.dp),
                        valueState = findPwdHolder.email,
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Email,
                        onAction = KeyboardActions {
                            if (findPwdHolder.email.value.trim().isEmpty()) return@KeyboardActions
                            keyboardController?.hide()
                        }
                    )
                    CertificationBtn(findPwdHolder.requestEmail) {
                        findPwdHolder.requestEmail.value = true
                    }
                }
                Divider(
                    Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(3.dp),
                    color = Grey1000
                )
                if (findPwdHolder.requestEmail.value) {
                    Column(
                        modifier = Modifier
                            .padding(top = 36.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            FlatInputField(
                                modifier = Modifier
                                    .padding(end = 182.dp)
                                    .align(Alignment.CenterStart),
                                valueState = findPwdHolder.code,
                                placeholder = "인증코드 입력",
                                enabled = true,
                                isSingleLine = true,
                                keyboardType = KeyboardType.Email,
                                onAction = KeyboardActions {
                                    if (findPwdHolder.code.value.trim()
                                        .isEmpty()
                                    ) return@KeyboardActions
                                    keyboardController?.hide()
                                }
                            )
                            Row(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = verificationTimer.collectAsState().value,
                                    modifier = Modifier.padding(end = 21.dp),
                                    color = Red500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = pretendardFamily
                                )
                                CertificationBtn(isCheck = findPwdHolder.requestCode) {
                                    if (!findPwdHolder.requestCode.value) {
                                        findPwdViewModel.timerStart()
                                    }
                                    findPwdHolder.requestCode.value = true
                                }
                            }
                        }
                        Divider(
                            Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .height(2.dp),
                            color = Grey1100
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Description("인증번호를 받지 못하셨나요?", modifier = Modifier.padding(start = 8.5.dp))
                        Column(
                            modifier = Modifier.width(IntrinsicSize.Max).padding(end = 8.5.dp)
                        ) {
                            ReSendBtn(findPwdHolder.requestCode) { findPwdViewModel.timerStart() }
                            Divider(
                                Modifier
                                    .padding(top = 2.dp)
                                    .height(1.dp),
                                color = Grey700
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CertificationBtn(isCheck: MutableState<Boolean>, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(38.dp),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, Red200),
        colors = if (isCheck.value) {
            ButtonDefaults.buttonColors(Red400)
        } else {
            ButtonDefaults.buttonColors(Color.White)
        }
    ) {
        Text(
            text = "인증 요청",
            color = if (isCheck.value) {
                Color.White
            } else {
                Red400
            },
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily
        )
    }
}
