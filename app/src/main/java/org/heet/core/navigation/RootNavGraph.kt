package org.heet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.heet.presentation.home.HomeScreen
import org.heet.presentation.splash.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            SplashScreen(navController = navController)
        }
        composable(route = Graph.HOME) {
            HomeScreen()
        }
        authNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val SIGNUP = "sign_up_graph"
    const val FORGOT = "forgot_graph"
    const val HOMETOWN = "hometown_graph"
    const val FOLLOWING = "following_graph"
    const val MY_PAGE = "my_page_graph"
}
