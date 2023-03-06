package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.DetailScreen
import org.heet.presentation.declaration.DeclarationFinishScreen
import org.heet.presentation.declaration.DeclarationScreen
import org.heet.presentation.home.following.FollowingListScreen
import org.heet.presentation.home.hometown.AddressScreen
import org.heet.presentation.home.hometown.CommentScreen
import org.heet.presentation.home.mypage.AboutUsScreen
import org.heet.presentation.home.mypage.ModifyProfileScreen
import org.heet.presentation.home.mypage.SettingScreen
import org.heet.presentation.home.mypage.TermsScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.Declaration.route
    ) {
        composable(route = DetailScreen.Declaration.route) {
            DeclarationScreen(navController = navController)
        }
        composable(route = DetailScreen.DeclarationFinish.route) {
            DeclarationFinishScreen(navController = navController)
        }
        composable(route = DetailScreen.Comment.route) {
            CommentScreen(navController = navController)
        }
        composable(route = DetailScreen.Address.route) {
            AddressScreen(navController = navController)
        }
        composable(route = DetailScreen.Setting.route) {
            SettingScreen(navController = navController)
        }
        composable(route = DetailScreen.ModifyProfile.route) {
            ModifyProfileScreen(navController = navController)
        }
        composable(route = DetailScreen.AboutUs.route) {
            AboutUsScreen(navController = navController)
        }
        composable(route = DetailScreen.Terms.route) {
            TermsScreen(navController = navController)
        }
        composable(route = DetailScreen.FollowingList.route) {
            FollowingListScreen(navController = navController)
        }
    }
}
