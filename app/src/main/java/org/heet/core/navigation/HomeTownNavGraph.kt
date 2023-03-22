package org.heet.core.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import org.heet.core.navigation.navscreen.HomeTownScreen
import org.heet.presentation.declaration.DeclarationFinishScreen
import org.heet.presentation.declaration.DeclarationScreen
import org.heet.presentation.home.hometown.comment.CommentScreen
import org.heet.presentation.home.hometown.detail.DetailScreen
import org.heet.presentation.home.hometown.finduser.FindUserScreen
import org.heet.presentation.home.hometown.modifypost.ModifyPostScreen
import org.heet.presentation.home.hometown.post.PostScreen
import org.heet.presentation.home.hometown.verify.VerifyScreen
import org.heet.presentation.home.mypage.ScrapScreen
import org.heet.presentation.home.mypage.UserProfileScreen

fun NavGraphBuilder.homeTownNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOMETOWN,
        startDestination = Graph.HOME
    ) {
        composable(route = HomeTownScreen.Declaration.route) {
            DeclarationScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Post.route) {
            PostScreen(navController = navController)
        }
        composable(route = HomeTownScreen.DeclarationFinish.route) {
            DeclarationFinishScreen(navController = navController)
        }
        composable(route = HomeTownScreen.UserProfile.route) {
            UserProfileScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Scrap.route) {
            ScrapScreen(navController = navController)
        }
        composable(route = HomeTownScreen.FindUser.route) {
            FindUserScreen(navController = navController)
        }
        composable(route = HomeTownScreen.Verify.route) {
            VerifyScreen(navController = navController)
        }
        composable(
            route = HomeTownScreen.PostModify.route + "/{post_id}",
            arguments = listOf(
                navArgument("post_id") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("post_id")?.let {
                ModifyPostScreen(
                    navController = navController,
                    postId = it
                )
            }
        }
        composable(
            route = HomeTownScreen.Detail.route + "/{post_id}",
            arguments = listOf(
                navArgument("post_id") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("post_id")?.let {
                DetailScreen(
                    post_id = it,
                    navController = navController
                )
            }
        }
        composable(
            route = HomeTownScreen.Comment.route + "/{post_id}",
            arguments = listOf(
                navArgument("post_id") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("post_id")?.let {
                CommentScreen(
                    post_id = it,
                    navController = navController
                )
            }
        }
    }
}
