package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.heet.presentation.findpwd.PasswordScreen
import org.heet.presentation.home.HomeScreen
import org.heet.presentation.join.JoinCertificationScreen
import org.heet.presentation.join.JoinEmailPwdScreen
import org.heet.presentation.join.JoinFinish
import org.heet.presentation.join.JoinIdScreen
import org.heet.presentation.login.LoginScreen
import org.heet.presentation.neighborhood.NeighborhoodSettingScreen
import org.heet.presentation.neighborhood.SettingFinishScreen
import org.heet.presentation.resetpassword.ResetPasswordScreen
import org.heet.presentation.splash.SplashScreen

@Composable
fun HeetNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HeetScreens.SplashScreen.name) {
        composable(HeetScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(HeetScreens.JoinCertificationScreen.name) {
            JoinCertificationScreen(navController = navController)
        }
        composable(HeetScreens.JoinEmailPwdScreen.name) {
            JoinEmailPwdScreen(navController = navController)
        }
        composable(HeetScreens.JoinFinish.name) {
            JoinFinish(navController = navController)
        }
        composable(HeetScreens.JoinIdScreen.name) {
            JoinIdScreen(navController = navController)
        }
        composable(HeetScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(HeetScreens.NeighborhoodSettingScreen.name) {
            NeighborhoodSettingScreen(navController = navController)
        }
        composable(HeetScreens.SettingFinishScreen.name) {
            SettingFinishScreen(navController = navController)
        }
        composable(HeetScreens.FindPasswordScreen.name) {
            PasswordScreen(navController = navController)
        }
        composable(HeetScreens.ResetPasswordScreen.name) {
            ResetPasswordScreen(navController = navController)
        }
        composable(HeetScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}
