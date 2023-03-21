package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.SignUpScreen
import org.heet.presentation.signup.email.SignUpEmailScreen
import org.heet.presentation.signup.id.SignUpIdScreen
import org.heet.presentation.signup.pwd.SignUpPwd
import org.heet.presentation.signup.residence.ResidenceScreen
import org.heet.presentation.signup.welcome.WelcomeScreen

fun NavGraphBuilder.signupNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SIGNUP,
        startDestination = SignUpScreen.SignUpEmail.route
    ) {
        composable(route = SignUpScreen.SignUpEmail.route) {
            SignUpEmailScreen(navController = navController)
        }
        composable(route = SignUpScreen.SignUpPwd.route) {
            SignUpPwd(navController = navController)
        }
        composable(route = SignUpScreen.SignUpId.route) {
            SignUpIdScreen(navController = navController)
        }
        composable(route = SignUpScreen.Residence.route) {
            ResidenceScreen(navController = navController)
        }
        composable(route = SignUpScreen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
    }
}
