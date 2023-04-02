package org.heet.presentation.declaration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.Finish
import org.heet.core.navigation.Graph
import org.heet.ui.theme.Grey800
import org.heet.ui.theme.Red100
import org.heet.ui.theme.Red500
import org.heet.util.pretendardFamily

@Composable
fun DeclarationFinishScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(19.dp))
        Finish(
            modifier = Modifier.align(Alignment.End).padding(end = 19.dp)
                .clickable { navController.navigate(Graph.HOME) },
        )
        Spacer(modifier = Modifier.height(226.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_check_33),
            contentDescription = stringResource(id = R.string.check),
        )
        Spacer(modifier = Modifier.height(19.dp))
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            Text(
                text = stringResource(id = R.string.declaration_finish_thank_you),
                color = Red500,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = pretendardFamily,
            )
            Divider(
                modifier = Modifier.padding(top = 1.dp).height(2.dp),
                color = Red100,
            )
        }
        Spacer(modifier = Modifier.height(59.dp))
        Text(
            text = stringResource(id = R.string.declaration_finish_notice),
            color = Grey800,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = pretendardFamily,
            textAlign = TextAlign.Center,
        )
    }
}
