package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.heet.presentation.home.HomeScreen
import org.heet.presentation.join.JoinScreen
import org.heet.presentation.login.LoginScreen
import org.heet.presentation.password.PasswordScreen
import org.heet.presentation.splash.SplashScreen

@Composable
fun HeetNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HeetScreens.SplashScreen.name) {
        composable(HeetScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(HeetScreens.JoinScreen.name) {
            JoinScreen(navController = navController)
        }
        composable(HeetScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(HeetScreens.PasswordScreen.name) {
            PasswordScreen(navController = navController)
        }
        composable(HeetScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}
