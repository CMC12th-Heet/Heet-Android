package org.heet.presentation.home.hometown.verify

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import org.heet.R
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Red500
import org.heet.util.pretendardFamily
import kotlin.math.abs

@Composable
fun VerifyScreen(
    navController: NavController,
    verifyViewModel: VerifyViewModel = hiltViewModel(),
) {
    val isVerifySuccess = verifyViewModel.isVerifySuccess.collectAsState().value
    val isProperLocation = verifyViewModel.isProperLocation.collectAsState().value
    val latitude = remember { mutableStateOf("") }
    val longitude = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(isVerifySuccess) {
        if (isVerifySuccess) {
            navController.popBackStack()
        }
    }
    LaunchedEffect(true) {
        requestPermission(context, latitude, longitude)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.verify_notice),
            color = Grey400,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = pretendardFamily,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(29.dp))
        if (!isProperLocation) {
            Text(
                text = stringResource(id = R.string.verify_different_location),
                color = Red500,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = pretendardFamily,
            )
        } else {
            Text(
                text = "  ",
                color = Red500,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = pretendardFamily,
            )
        }
        Spacer(modifier = Modifier.height(23.dp))
        Surface(
            modifier = Modifier.clickable {
                verifyViewModel.postVerify(
                    latitude.value,
                    longitude.value,
                )
            },
            shape = RoundedCornerShape(21.dp),
            border = BorderStroke(width = 1.dp, color = Red500),
        ) {
            Text(
                text = stringResource(id = R.string.verify_try_verify),
                modifier = Modifier.padding(horizontal = 33.dp, vertical = 8.dp),
                color = Red500,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily,
            )
        }
        Spacer(modifier = Modifier.height(98.dp))
        Text(
            text = stringResource(id = R.string.cancel),
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            color = Grey400,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = pretendardFamily,
        )
    }
}

fun requestPermission(
    context: Context,
    latitude: MutableState<String>,
    longitude: MutableState<String>,
) {
    TedPermission.create()
        .setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener {
                        latitude.value = abs(it.longitude).toString()
                        longitude.value = abs(it.latitude).toString()
                    }
                }
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            }
        })
        .setPermissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        .check()
}
