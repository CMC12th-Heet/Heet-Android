package org.heet.core.navigation.navscreen

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
}
