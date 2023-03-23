package org.heet.presentation.home.following.followinglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.Back
import org.heet.components.DotDivider
import org.heet.components.EmptyText
import org.heet.components.Title
import org.heet.ui.theme.Red500
import org.heet.ui.theme.White600
import org.heet.util.pretendardFamily

@Composable
fun FollowingListScreen(
    navController: NavController,
    followingListViewModel: FollowingListViewModel = hiltViewModel()
) {
    followingListViewModel.getFollowingList()
    val followingList = followingListViewModel.followingList.collectAsState().value

    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, top = 14.dp, end = 20.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Back { navController.popBackStack() }
                Title(title = "팔로잉")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(21.dp))
            DotDivider()
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(followingList.size) { index ->
            val follow = remember { mutableStateOf(true) }

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
                                painter = painterResource(id = R.drawable.img_profile_following),
                                contentDescription = "profileImage"
                            )
                        }
                        Text(
                            text = followingList[index].username,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = pretendardFamily
                        )
                    }
                    Text(
                        text = "팔로우",
                        modifier = Modifier.padding(end = 13.dp).clickable {
                            followingListViewModel.postFollow(followingList[index].user_id.toString())
                            follow.value = !follow.value
                        },
                        color = if (follow.value) Red500 else White600,
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
