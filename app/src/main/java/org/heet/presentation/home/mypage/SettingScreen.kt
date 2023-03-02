package org.heet.presentation.home.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.components.Back
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.data.datasource.LocalSettingDataSource
import org.heet.ui.theme.Grey500
import org.heet.ui.theme.Red200
import org.heet.util.pretendardFamily

@Composable
fun SettingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title(text = "설정")
                EmptyText()
            }
            LazyColumn(
                modifier = Modifier.padding(start = 10.dp, top = 43.5.dp, end = 7.dp)
            ) {
                items(LocalSettingDataSource().loadSettingItems()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            it.title,
                            color = Grey500,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                        Image(
                            painter = painterResource(id = org.heet.R.drawable.ic_grey_next),
                            contentDescription = "move",
                            modifier = Modifier.clickable {
                                navController.navigate(it.destination.route)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Text(
                text = "V.1.0.0",
                color = Red200,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "HEET",
                color = Color.Black,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "문의 및 건의: makeideastrue1@gmail.com",
                color = Color.Black,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Spacer(modifier = Modifier.height(46.dp))
        }
    }
}
