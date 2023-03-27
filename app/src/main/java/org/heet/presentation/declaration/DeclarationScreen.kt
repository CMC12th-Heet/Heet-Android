package org.heet.presentation.declaration

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.data.datasource.LocalDeclarationReasonDataSource
import org.heet.data.local.DeclarationReasonItem
import org.heet.ui.theme.Black50
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White550
import org.heet.util.EmailManager
import org.heet.util.pretendardFamily

@Composable
fun DeclarationScreen(navController: NavController) {
    val isChecked = remember { mutableStateOf(true) }
    val reportLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            navController.navigate(HomeTownScreen.DeclarationFinish.route)
        }
    val declarationReasons =
        remember { mutableStateOf(LocalDeclarationReasonDataSource().loadDeclarationReasons()) }
    val declarationReason = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 18.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TopBar(navController, isChecked, reportLauncher, declarationReason.value)
        }
        Spacer(modifier = Modifier.height(56.5.dp))
        Notice()
        Spacer(modifier = Modifier.height(8.5.dp))
        Divider(
            Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = White550,
        )
        Spacer(modifier = Modifier.height(22.dp))
        DeclarationReasons(declarationReasons, declarationReason, context)
    }
}

@Composable
private fun TopBar(
    navController: NavController,
    isChecked: MutableState<Boolean>,
    reportLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    declarationReason: String,
) {
    Back {
        navController.popBackStack()
    }
    Title(stringResource(id = R.string.declaration_title))
    if (isChecked.value) {
        Next { EmailManager.sendEmailToAdmin(reportLauncher, declarationReason) }
    } else {
        EmptyText()
    }
}

@Composable
private fun Notice() {
    Text(
        text = stringResource(id = R.string.declaration_notice_reason),
        modifier = Modifier.padding(start = 10.dp),
        color = Black50,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = pretendardFamily,
    )
}

@Composable
private fun DeclarationReasons(
    declarationReasons: MutableState<List<DeclarationReasonItem>>,
    declarationReason: MutableState<String>,
    context: Context,
) {
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, end = 7.dp),
    ) {
        items(declarationReasons.value.size) { outerIndex ->
            val color = if (declarationReasons.value[outerIndex].isSelected) {
                Red500
            } else {
                Grey400
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = declarationReasons.value[outerIndex].reason),
                    modifier = Modifier.clickable {
                        declarationReasons.value =
                            declarationReasons.value.mapIndexed { innerIndex, declaration ->
                                if (outerIndex == innerIndex) {
                                    declarationReason.value = context.getString(declaration.reason)
                                    declaration.copy(isSelected = !declaration.isSelected)
                                } else {
                                    declaration.copy(isSelected = false)
                                }
                            }
                    },
                    color = color,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = stringResource(id = R.string.next),
                    colorFilter = ColorFilter.tint(color),
                )
            }
            Spacer(modifier = Modifier.height(13.dp))
        }
    }
}
