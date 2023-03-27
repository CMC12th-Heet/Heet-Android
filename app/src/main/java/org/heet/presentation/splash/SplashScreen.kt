package org.heet.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.heet.R
import org.heet.core.navigation.Graph
import org.heet.core.navigation.navscreen.AuthScreen

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LaunchedEffect(key1 = true, block = {
            delay(1500)
            navController.popBackStack()
            if (splashViewModel.getToken() == "") {
                navController.navigate(AuthScreen.Login.route)
            } else {
                navController.navigate(Graph.HOME)
            }
        })

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "heet_logo",
        )
    }
}
