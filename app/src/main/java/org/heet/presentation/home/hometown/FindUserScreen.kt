package org.heet.presentation.home.hometown

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.Back
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FindUserScreen(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val didSearch = remember { mutableStateOf(true) }
    val id = remember { mutableStateOf("") }
    val horizontalAlignment = if (didSearch.value) Alignment.Start else Alignment.CenterHorizontally

    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Back { navController.popBackStack() }
                Title(text = "??????")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(12.dp))
            FindUserTextField(id = id, keyboardController = keyboardController)
            Spacer(modifier = Modifier.height(12.dp))
            if (didSearch.value) {
                Text(
                    text = "????????????",
                    modifier = Modifier.padding(start = 15.dp),
                    color = Black700,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendardFamily
                )
            } else {
                Text(
                    text = "????????? ?????? ????????? ????????? ?????????",
                    color = Black700,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendardFamily
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(22.dp))
        }
        if (didSearch.value) {
            items(listOf(1, 2, 3, 4, 5, 6)) {
                Surface(
                    shape = RoundedCornerShape(7.dp),
                    elevation = 7.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().padding(start = 6.dp, top = 7.dp, bottom = 7.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_profile_find_user),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(
                            text = "heet_member",
                            color = Red500,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
        } else {
            item {
                Text(
                    text = "??????\nex) HEET_USER0318",
                    modifier = Modifier.fillMaxWidth(),
                    color = Grey350,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = pretendardFamily,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun FindUserTextField(
    id: MutableState<String>,
    keyboardController: SoftwareKeyboardController?
) {
    BasicTextField(
        value = id.value,
        onValueChange = {
            if (it.length <= 20) {
                id.value = it
            }
        },
        modifier = Modifier.padding(top = 12.dp),
        enabled = true,
        textStyle = TextStyle.Default.copy(
            color = Black350,
            fontSize = 13.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            if (id.value.trim().isEmpty()) return@KeyboardActions
            keyboardController?.hide()
        },
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(30.dp), color = White700)
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                if (id.value.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "?????? ??????",
                            fontFamily = pretendardFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_search_white_14),
                            contentDescription = "search"
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        innerTextField()
                        Image(
                            painter = painterResource(id = R.drawable.ic_circle_cancel_white_16),
                            contentDescription = "cancel",
                            modifier = Modifier.clickable { id.value = "" }
                        )
                    }
                }
            }
        }
    )
}
