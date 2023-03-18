package org.heet.presentation.home.hometown.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.heet.R
import org.heet.components.*
import org.heet.data.model.request.RequestPostComment
import org.heet.ui.theme.*
import org.heet.util.pretendardFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentScreen(
    post_id: String,
    navController: NavController,
    commentViewModel: CommentViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val comment = remember {
        mutableStateOf("")
    }
    val commentList by commentViewModel.commentList.collectAsState()

    LaunchedEffect(commentList) {
        commentViewModel.getComment(post_id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back { navController.popBackStack() }
                Title("댓글")
                EmptyText()
            }
            Spacer(modifier = Modifier.height(13.dp))
            DotDivider()
            LazyColumn(modifier = Modifier.padding(top = 23.dp, bottom = 47.dp)) {
                items(commentList) {
                    CommentList(it.user.username, it.content)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        Surface(
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            CommentField(comment, commentViewModel, post_id, keyboardController)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CommentField(
    comment: MutableState<String>,
    commentViewModel: CommentViewModel,
    post_id: String,
    keyboardController: SoftwareKeyboardController?
) {
    Row(
        modifier = Modifier.padding(start = 19.dp, end = 11.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_grey_30),
            contentDescription = "profile",
            modifier = Modifier.padding(vertical = 7.dp)
        )
        Spacer(modifier = Modifier.width(11.dp))
        CommentTextField(
            comment = comment,
            commentViewModel = commentViewModel,
            post_id = post_id,
            keyboardController = keyboardController
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CommentTextField(
    comment: MutableState<String>,
    commentViewModel: CommentViewModel,
    post_id: String,
    keyboardController: SoftwareKeyboardController?
) {
    BasicTextField(
        value = comment.value,
        onValueChange = {
            comment.value = it
        },
        modifier = Modifier.padding(vertical = 7.dp),
        enabled = true,
        textStyle = TextStyle.Default.copy(
            color = Grey500,
            fontSize = 13.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            if (comment.value.trim().isEmpty()) return@KeyboardActions
            commentViewModel.postComment(
                postId = post_id,
                requestPostComment = RequestPostComment(comment.value)
            )
            comment.value = ""
            keyboardController?.hide()
        },
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(20.dp), color = White200)
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                if (comment.value.isEmpty()) {
                    Text(
                        text = "댓글 달기..",
                        fontFamily = pretendardFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
private fun CommentList(username: String, content: String) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_grey_30),
            contentDescription = "profile"
        )
        Column(modifier = Modifier.padding(start = 7.dp)) {
            Text(
                text = username,
                fontSize = 10.sp,
                color = White900,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
            Text(
                text = content,
                fontSize = 13.sp,
                color = Grey450,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendardFamily
            )
        }
    }
}
