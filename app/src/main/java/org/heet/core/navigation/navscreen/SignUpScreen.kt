package org.heet.core.navigation.navscreen

sealed class SignUpScreen(val route: String) {
    object SignUpEmail : SignUpScreen(route = "SIGN_UP_EMAIL")
    object SignUpPwd : SignUpScreen(route = "SIGN_UP_PWD")
    object SignUpId : SignUpScreen(route = "SIGN_UP_ID")
    object SignUpWelcome : SignUpScreen(route = "SIGN_UP_WELCOME")
    object SignUpResidence : SignUpScreen(route = "SIGN_UP_RESIDENCE")
    object SignUpResidenceWelcome : SignUpScreen(route = "SIGN_UP_RESIDENCE_WELCOME")
}
