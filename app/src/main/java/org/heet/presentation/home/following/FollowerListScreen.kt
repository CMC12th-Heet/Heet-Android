package org.heet.presentation.home.following

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
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
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.ui.theme.Red200
import org.heet.util.pretendardFamily

@Composable
fun FollowerListScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Back { navController.popBackStack() }
                Title(text = "팔로워")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(21.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(listOf(1, 2, 3, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1)) {
            Surface(
                shape = RoundedCornerShape(7.dp),
                elevation = 10.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            modifier = Modifier.padding(
                                start = 6.dp,
                                top = 7.dp,
                                end = 8.dp,
                                bottom = 7.dp
                            ).size(30.dp)
                        ) {
                            Image(
                                painter = painterResource(id = org.heet.R.drawable.img_following_default),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "heet_member",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Text(
                        text = "팔로우",
                        modifier = Modifier.padding(end = 13.dp),
                        color = Red200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = pretendardFamily
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
