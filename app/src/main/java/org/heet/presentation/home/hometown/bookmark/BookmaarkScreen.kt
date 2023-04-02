package org.heet.presentation.home.hometown.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.Back
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.util.pretendardFamily

@Composable
fun BookmarkScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Back { navController.popBackStack() }
            Title(title = "스크랩")
            EmptyText()
        }
        Spacer(modifier = Modifier.height(21.dp))
        DotDivider()
        Spacer(modifier = Modifier.height(11.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) {
                Surface(shape = RoundedCornerShape(5.dp)) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.img_profile_modify),
                            contentDescription = "image",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Row(
                            modifier = Modifier.align(Alignment.TopStart)
                                .padding(start = 6.dp, top = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_location_white_11),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "중구 약수동",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = pretendardFamily,
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
