package org.heet.core.navigation.navscreen

sealed class MyPageScreen(val route: String) {
    object AboutUs : MyPageScreen(route = "about_us")
    object Terms : MyPageScreen(route = "terms")
    object Setting : MyPageScreen(route = "setting")
    object ModifyProfile : MyPageScreen(route = "modify_profile")
    object Scrap : MyPageScreen(route = "scrap")
}
