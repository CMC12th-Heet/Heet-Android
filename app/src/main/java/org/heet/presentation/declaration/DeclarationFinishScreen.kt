package org.heet.presentation.declaration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.Finish
import org.heet.core.navigation.Graph
import org.heet.ui.theme.Black300
import org.heet.ui.theme.Red100
import org.heet.ui.theme.Red200
import org.heet.util.pretendardFamily

@Composable
fun DeclarationFinishScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(19.dp))
        Finish(modifier = Modifier.align(Alignment.End).padding(end = 19.dp)) {
            navController.navigate(Graph.HOME)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_big_black_check),
            contentDescription = null,
            modifier = Modifier.padding(top = 226.dp)
        )
        Column(modifier = Modifier.width(IntrinsicSize.Max).padding(top = 19.dp)) {
            Text(
                text = "신고해주셔서 감사합니다",
                color = Red200,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = pretendardFamily
            )
            Divider(
                modifier = Modifier.padding(top = 1.dp).height(2.dp),
                color = Red100
            )
        }
        Text(
            text = "HEET 서비스 팀에서 정책에 위반되는지 검토 후 결과를\n알려드리겠습니다. 안전한 HEET 서비스가 될 수 있도록\n도와주셔서 감사합니다.",
            modifier = Modifier.padding(top = 59.dp),
            color = Black300,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily,
            textAlign = TextAlign.Center
        )
    }
}
