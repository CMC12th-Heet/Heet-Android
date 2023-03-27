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
import org.heet.ui.theme.Grey400
import org.heet.ui.theme.Red500
import org.heet.util.pretendardFamily
import timber.log.Timber
import kotlin.math.abs

@Composable
fun VerifyScreen(
    navController: NavController,
    verifyViewModel: VerifyViewModel = hiltViewModel(),
) {
    val isVerify = remember { mutableStateOf(true) }
    val x = remember { mutableStateOf("") }
    val y = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        verifyViewModel.verify.collect {
            isVerify.value = it
            if (it) {
                navController.popBackStack()
            }
        }
    }

    requestPermission(context, x, y)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "첫 게시글을 작성하기 전\n위치 인증을 진행해주세요!",
            color = Grey400,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = pretendardFamily,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(29.dp))
        if (!isVerify.value) {
            Text(
                text = "*초기 설정과 다른 위치입니다.",
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
                    x.value,
                    y.value,
                )
            },
            shape = RoundedCornerShape(21.dp),
            border = BorderStroke(width = 1.dp, color = Red500),
        ) {
            Text(
                text = "인증하기",
                color = Red500,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pretendardFamily,
                modifier = Modifier.padding(horizontal = 33.dp, vertical = 8.dp),
            )
        }
        Spacer(modifier = Modifier.height(98.dp))
        Text(
            text = "취소",
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

fun requestPermission(context: Context, x: MutableState<String>, y: MutableState<String>) {
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
                        Timber.d("${x.value} ${y.value}")
                        x.value = abs(it.longitude).toString()
                        y.value = abs(it.latitude).toString()
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
