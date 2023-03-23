package org.heet.core.navigation.navscreen

sealed class ForgotScreen(val route: String) {
    object ForgotEmail : ForgotScreen(route = "FORGOT_EMAIL")
    object ForgotPwd : ForgotScreen(route = "FORGOT_PWD")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
