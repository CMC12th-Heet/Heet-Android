package org.heet.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.heet.core.navigation.navscreen.DetailScreen
import org.heet.presentation.declaration.DeclarationFinishScreen
import org.heet.presentation.declaration.DeclarationScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.Declaration.route
    ) {
        composable(route = DetailScreen.Declaration.route) {
            DeclarationScreen(navController = navController)
        }
        composable(route = DetailScreen.DeclarationFinish.route) {
            DeclarationFinishScreen(navController = navController)
        }
    }
}
