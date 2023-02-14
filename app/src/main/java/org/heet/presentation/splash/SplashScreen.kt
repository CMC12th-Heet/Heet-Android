package org.heet.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.heet.R
import org.heet.core.navigation.HeetScreens
import org.heet.ui.theme.Red200

@Composable
fun SplashScreen(navController: NavController) {
    Surface(color = Red200, modifier = Modifier.fillMaxSize()) {
        LaunchedEffect(key1 = true, block = {
            delay(2000)
            navController.navigate(HeetScreens.LoginScreen.name)
        })

        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_image_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 188.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_text_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
            )
        }
    }
}
