package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.heet.core.navigation.navscreen.BottomBarScreen
import org.heet.presentation.home.following.FollowingScreen
import org.heet.presentation.home.hometown.HomeTownScreen
import org.heet.presentation.home.mypage.MyPage

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Hometown.route
    ) {
        composable(route = BottomBarScreen.Hometown.route) {
            HomeTownScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Following.route) {
            FollowingScreen(navController = navController)
        }
        composable(route = BottomBarScreen.MyPage.route) {
            MyPage(navController = navController)
        }
        homeTownNavGraph(navController = navController)
        followingNavGraph(navController = navController)
        myPageNavGraph(navController = navController)
    }
}
