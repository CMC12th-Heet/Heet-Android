package org.heet.data.datasource

import org.heet.core.navigation.navscreen.MyPageScreen
import org.heet.data.local.SettingItem

class LocalSettingDataSource {

    fun loadSettingItems(): List<SettingItem> {
        return listOf(
            SettingItem("프로필 수정", MyPageScreen.ModifyProfile),
            SettingItem("회사 소개", MyPageScreen.AboutUs),
            SettingItem("약관", MyPageScreen.Terms),
        )
    }
}
