package org.heet.core.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import org.heet.core.navigation.navscreen.ForgotScreen
import org.heet.presentation.forgot.email.ForgotEmailScreen
import org.heet.presentation.forgot.pwd.ForgotPwdScreen

fun NavGraphBuilder.forgotNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.FORGOT,
        startDestination = ForgotScreen.ForgotEmail.route
    ) {
        composable(route = ForgotScreen.ForgotEmail.route) {
            ForgotEmailScreen(navController = navController)
        }
        composable(
            route = ForgotScreen.ForgotPwd.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("email")?.let {
                ForgotPwdScreen(
                    email = it,
                    navController = navController
                )
            }
        }
    }
}
