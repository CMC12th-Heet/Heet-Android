package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.heet.presentation.login.LoginScreen
import org.heet.presentation.splash.SplashScreen

@Composable
fun HeetNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HeetScreens.SplashScreen.name) {
        composable(HeetScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(HeetScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
    }
}
