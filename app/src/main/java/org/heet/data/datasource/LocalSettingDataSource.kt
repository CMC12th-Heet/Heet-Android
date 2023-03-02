package org.heet.data.datasource

import org.heet.core.navigation.navscreen.DetailScreen
import org.heet.data.local.SettingItem

class LocalSettingDataSource {

    fun loadSettingItems(): List<SettingItem> {
        return listOf(
            SettingItem("프로필 수정", DetailScreen.ModifyProfile),
            SettingItem("회사 소개", DetailScreen.AboutUs),
            SettingItem("약관", DetailScreen.Terms)
        )
    }
}
