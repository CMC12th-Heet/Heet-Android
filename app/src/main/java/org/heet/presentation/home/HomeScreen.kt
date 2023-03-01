package org.heet.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.heet.R
import org.heet.core.navigation.HomeNavGraph
import org.heet.core.navigation.navscreen.BottomBarScreen
import org.heet.ui.theme.Grey200
import org.heet.ui.theme.Red200
import org.heet.util.pretendardFamily

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    val isWrite = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = { WriteButton(isWrite = isWrite) }
    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
private fun WriteButton(isWrite: MutableState<Boolean>) {
    val writeImage = if (isWrite.value) {
        R.drawable.ic_white_wrtie
    } else {
        R.drawable.ic_red_write
    }
    val backgroundColor = if (isWrite.value) {
        Red200
    } else {
        Color.White
    }
    FloatingActionButton(
        onClick = { isWrite.value = !isWrite.value },
        modifier = Modifier
            .padding(end = 26.dp, bottom = 21.dp)
            .size(42.dp),
        shape = RoundedCornerShape(7.dp),
        backgroundColor = backgroundColor,
        elevation = FloatingActionButtonDefaults.elevation(2.dp)
    ) {
        Image(
            painter = painterResource(writeImage),
            contentDescription = "write"
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Hometown,
        BottomBarScreen.FOLLOWING,
        BottomBarScreen.MyPage
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Color.White) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 41.dp, end = 38.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val background = Color.White
    val contentColor = if (selected) Red200 else Grey200

    Box(
        modifier = Modifier
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = screen.title,
                color = contentColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily
            )
        }
    }
}
