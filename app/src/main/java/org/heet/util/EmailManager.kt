package org.heet.util

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult

object EmailManager {

    fun sendEmailToAdmin(
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        content: String
    ) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("makeideastrue1@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "제목: HEET 서비스 부적절한 게시글 신고")
            putExtra(
                Intent.EXTRA_TEXT,
                "해당 유저의 ${content}의 정책 위반과 다른 유저에게 불쾌한 경험을 주는 게시글을 신고합니다. 빠른 검토와 처리 부탁드립니다. "
            )
        }
        launcher.launch(intent)
    }
}
