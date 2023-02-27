package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.ForgotScreen
import org.heet.presentation.forgot.ForgotEmailScreen
import org.heet.presentation.forgot.pwd.ForgotPwdScreen

fun NavGraphBuilder.forgotNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.FORGOT,
        startDestination = ForgotScreen.ForgotEmail.route
    ) {
        composable(route = ForgotScreen.ForgotEmail.route) {
            ForgotEmailScreen(navController = navController)
        }
        composable(route = ForgotScreen.ForgotPwd.route) {
            ForgotPwdScreen(navController = navController)
        }
    }
}
