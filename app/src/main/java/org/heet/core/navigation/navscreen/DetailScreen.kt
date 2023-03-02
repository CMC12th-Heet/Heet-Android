package org.heet.core.navigation.navscreen

sealed class DetailScreen(val route: String) {
    object Declaration : DetailScreen(route = "declaration")
    object DeclarationFinish : DetailScreen(route = "declaration_finish")
    object Comment : DetailScreen(route = "comment")
    object Address : DetailScreen(route = "address")
    object Setting : DetailScreen(route = "setting")
    object ModifyProfile : DetailScreen(route = "modify_profile")
    object AboutUs : DetailScreen(route = "about_us")
    object Terms : DetailScreen(route = "terms")
}
