package org.heet.data.datasource

import org.heet.data.local.TermItem

class LoadTermsDataSource {

    fun loadTerms(): List<TermItem> {
        return listOf(
            TermItem(
                "[필수] 개인정보 수집 및 이용 동의",
                "https://heetkr.notion.site/003152352a4e4f05b3607b1a790d3827",
                false,
            ),
            TermItem(
                "[필수] 이용약관 및 위치기반 서비스 이용약관 동의",
                "https://heetkr.notion.site/HEET-6590ce86b1b54526ad1fead10ae8f9b9",
                false,
            ),
            TermItem(
                "[필수] 만 14세 이상입니다.",
                "",
                false,
            ),
            TermItem(
                "[선택] 마케팅 활용 및 광고성 정보 수신 동의",
                "https://heetkr.notion.site/7d1df952675344f58640174795208fd6",
                false,
            ),
        )
    }
}
