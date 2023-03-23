package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.FollowingScreen
import org.heet.presentation.home.following.followerList.FollowerListScreen
import org.heet.presentation.home.following.followinglist.FollowingListScreen

fun NavGraphBuilder.followingNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.FOLLOWING,
        startDestination = Graph.HOME
    ) {
        composable(route = FollowingScreen.FollowingList.route) {
            FollowingListScreen(navController = navController)
        }
        composable(route = FollowingScreen.FollowerList.route) {
            FollowerListScreen(navController = navController)
        }
    }
}
