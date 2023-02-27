package org.heet.core.navigation.navscreen

import org.heet.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    object Hometown : BottomBarScreen(
        route = "HOMETOWN",
        title = "우리동네",
        icon = R.drawable.ic_nav_grey_location,
        icon_focused = R.drawable.ic_nav_red_location
    )

    object FOLLOWING : BottomBarScreen(
        route = "FOLLOWING",
        title = "팔로잉",
        icon = R.drawable.ic_nav_grey_following,
        icon_focused = R.drawable.ic_nav_red_following
    )

    object MyPage : BottomBarScreen(
        route = "MY_PAGE",
        title = "마이페이지",
        icon = R.drawable.ic_nav_grey_profile,
        icon_focused = R.drawable.ic_nav_red_profile
    )
}
