package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.presentation.declaration.DeclarationFinishScreen
import org.heet.presentation.declaration.DeclarationScreen
import org.heet.presentation.home.hometown.*
import org.heet.presentation.home.hometown.verify.VerifyScreen
import org.heet.presentation.home.mypage.ScrapScreen
import org.heet.presentation.home.mypage.UserProfileScreen

fun NavGraphBuilder.homeTownNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOMETOWN,
        startDestination = Graph.HOME
    ) {
        composable(route = HomeTownScreen.Address.route) {
            AddressScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Comment.route) {
            CommentScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Declaration.route) {
            DeclarationScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Post.route) {
            PostScreen(navController = navController)
        }
        composable(route = HomeTownScreen.DeclarationFinish.route) {
            DeclarationFinishScreen(navController = navController)
        }
        composable(route = HomeTownScreen.UserProfile.route) {
            UserProfileScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Scrap.route) {
            ScrapScreen(navController = navController)
        }
        composable(route = HomeTownScreen.FindUser.route) {
            FindUserScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Verify.route) {
            VerifyScreen(navController = navController)
        }
    }
}
