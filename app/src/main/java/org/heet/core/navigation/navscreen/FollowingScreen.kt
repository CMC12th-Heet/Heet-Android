package org.heet.core.navigation.navscreen

sealed class FollowingScreen(val route: String) {
    object FollowingList : FollowingScreen(route = "following_list")
    object FollowerList : FollowingScreen(route = "follower_list")
}
