package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.heet.presentation.declaration.DeclarationFinishScreen
import org.heet.presentation.declaration.DeclarationScreen
import org.heet.presentation.findpwd.PasswordScreen
import org.heet.presentation.home.HomeScreen
import org.heet.presentation.join.email.JoinCertificationScreen
import org.heet.presentation.join.id.JoinIdScreen
import org.heet.presentation.join.pwd.JoinEmailPwdScreen
import org.heet.presentation.join.residence.NeighborhoodSettingScreen
import org.heet.presentation.join.welcome.JoinFinish
import org.heet.presentation.join.welcome.SettingFinishScreen
import org.heet.presentation.login.LoginScreen
import org.heet.presentation.resetpwd.ResetPasswordScreen
import org.heet.presentation.splash.SplashScreen

@Composable
fun HeetNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HeetScreens.SplashScreen.name) {
        composable(HeetScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(HeetScreens.JoinEmailScreen.name) {
            JoinCertificationScreen(navController = navController)
        }
        composable(HeetScreens.JoinPwdScreen.name) {
            JoinEmailPwdScreen(navController = navController)
        }
        composable(HeetScreens.JoinWelcome.name) {
            JoinFinish(navController = navController)
        }
        composable(HeetScreens.JoinIdScreen.name) {
            JoinIdScreen(navController = navController)
        }
        composable(HeetScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(HeetScreens.ResidenceSettingScreen.name) {
            NeighborhoodSettingScreen(navController = navController)
        }
        composable(HeetScreens.ResidenceWelcomeScreen.name) {
            SettingFinishScreen(navController = navController)
        }
        composable(HeetScreens.FindPwdScreen.name) {
            PasswordScreen(navController = navController)
        }
        composable(HeetScreens.ResetPwdScreen.name) {
            ResetPasswordScreen(navController = navController)
        }
        composable(HeetScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(HeetScreens.DeclarationScreen.name) {
            DeclarationScreen(navController = navController)
        }
        composable(HeetScreens.DeclarationFinishScreen.name) {
            DeclarationFinishScreen(navController = navController)
        }
    }
}
