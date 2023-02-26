package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.heet.FollowingScreen
import org.heet.HomeTownScreen
import org.heet.MyPage
import org.heet.core.navigation.navscreen.BottomBarScreen

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
        composable(route = BottomBarScreen.FOLLOWING.route) {
            FollowingScreen(navController = navController)
        }
        composable(route = BottomBarScreen.MyPage.route) {
            MyPage(navController = navController)
        }
        detailsNavGraph(navController = navController)
    }
}
