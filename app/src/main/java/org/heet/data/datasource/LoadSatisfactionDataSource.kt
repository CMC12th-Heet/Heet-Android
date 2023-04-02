package org.heet.data.datasource

import org.heet.R
import org.heet.data.local.SatisfactionItem

class LoadSatisfactionDataSource {

    fun loadSatisfactionItems(): List<SatisfactionItem> {
        return listOf(
            SatisfactionItem(R.drawable.ic_feel_worst, "화나요.", false),
            SatisfactionItem(R.drawable.ic_feel_bad, "별로에요.", false),
            SatisfactionItem(R.drawable.ic_feel_soso, "그럭저럭?", false),
            SatisfactionItem(R.drawable.ic_feel_good, "좋았어요!", false),
            SatisfactionItem(R.drawable.ic_feel_awesome, "재방문100%", false),
        )
    }
}
