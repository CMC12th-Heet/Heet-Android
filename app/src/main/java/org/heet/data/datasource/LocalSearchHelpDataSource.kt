package org.heet.data.datasource

import org.heet.data.local.SearchHelpItem

class LocalSearchHelpDataSource {

    fun loadSearchHelps(): List<SearchHelpItem> {
        return listOf(
            SearchHelpItem("가게명+동네명", "ex) 이디야 논현점/버거킹 논현점.."),
            SearchHelpItem("주소", "ex) 서울시 강남구 OO로.."),
            SearchHelpItem("동네명", "ex) 경기도 용인시 수지구..")
        )
    }
}
