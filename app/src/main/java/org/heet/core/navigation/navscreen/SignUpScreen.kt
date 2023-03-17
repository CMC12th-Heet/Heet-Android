package org.heet.core.navigation.navscreen

sealed class SignUpScreen(val route: String) {
    object SignUpEmail : SignUpScreen(route = "SIGN_UP_EMAIL")
    object SignUpPwd : SignUpScreen(route = "SIGN_UP_PWD")
    object SignUpId : SignUpScreen(route = "SIGN_UP_ID")
    object SignUpResidence : SignUpScreen(route = "SIGN_UP_RESIDENCE")
    object Welcome : SignUpScreen(route = "WELCOME")
}
