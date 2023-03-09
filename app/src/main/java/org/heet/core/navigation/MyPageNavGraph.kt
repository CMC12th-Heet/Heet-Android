package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.MyPageScreen
import org.heet.presentation.home.mypage.*

fun NavGraphBuilder.myPageNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MY_PAGE,
        startDestination = Graph.HOME
    ) {
        composable(route = MyPageScreen.Setting.route) {
            SettingScreen(navController = navController)
        }
        composable(route = MyPageScreen.AboutUs.route) {
            AboutUsScreen(navController = navController)
        }
        composable(route = MyPageScreen.ModifyProfile.route) {
            ModifyProfileScreen(navController = navController)
        }
        composable(route = MyPageScreen.Terms.route) {
            TermsScreen(navController = navController)
        }
        composable(route = MyPageScreen.Scrap.route) {
            ScrapScreen(navController = navController)
        }
    }
}
