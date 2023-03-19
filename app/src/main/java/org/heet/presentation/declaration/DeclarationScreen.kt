package org.heet.presentation.declaration

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.data.datasource.LocalDeclarationDataSource
import org.heet.ui.theme.Black50
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White550
import org.heet.util.EmailManager
import org.heet.util.pretendardFamily

@Composable
fun DeclarationScreen(navController: NavController) {
    var declarationList by remember { mutableStateOf(LocalDeclarationDataSource().loadDeclarations()) }
    val isCheck = remember {
        mutableStateOf(true)
    }
    var report by remember { mutableStateOf("") }
    val repostLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        navController.navigate(HomeTownScreen.DeclarationFinish.route)
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
                Title("신고하기")
                if (isCheck.value) {
                    Next {
                        EmailManager.sendEmailToAdmin(repostLauncher, report)
                    }
                } else {
                    EmptyText()
                }
            }
            Text(
                text = "이 게시물에 대한 신고 이유를 선택해주세요.",
                modifier = Modifier.padding(start = 10.dp, top = 56.5.dp),
                color = Black50,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = pretendardFamily
            )
            Divider(
                Modifier
                    .padding(top = 8.5.dp)
                    .fillMaxWidth()
                    .height(2.dp),
                color = White550
            )
            LazyColumn(
                modifier = Modifier.padding(top = 22.dp, start = 10.dp, end = 7.dp)
            ) {
                items(declarationList.size) { index ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = declarationList[index].content,
                            modifier = Modifier.clickable {
                                declarationList = declarationList.mapIndexed { j, declaration ->
                                    if (index == j) {
                                        report = declaration.content
                                        declaration.copy(isSelected = !declaration.isSelected)
                                    } else declaration.copy(isSelected = false)
                                }
                            },
                            color = if (declarationList[index].isSelected) {
                                Red500
                            } else {
                                Grey400
                            }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_next_grey_24),
                            contentDescription = "detail"
                        )
                    }
                    Spacer(modifier = Modifier.height(13.dp))
                }
            }
        }
    }
}
