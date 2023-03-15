package org.heet.core.navigation.navscreen

sealed class HomeTownScreen(val route: String) {
    object Declaration : HomeTownScreen(route = "declaration")
    object DeclarationFinish : HomeTownScreen(route = "declaration_finish")
    object Comment : HomeTownScreen(route = "comment")
    object Scrap : HomeTownScreen(route = "scrap")
    object FindUser : HomeTownScreen(route = "find_user")
    object Post : HomeTownScreen(route = "post")
    object Address : HomeTownScreen(route = "address")
    object UserProfile : HomeTownScreen(route = "user_profile")
    object Verify : HomeTownScreen(route = "verify")
    object Detail : HomeTownScreen(route = "detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
