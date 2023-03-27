package org.heet.data.datasource

import org.heet.data.local.Hometown

class LocalHometownDataSource {

    fun loadHometowns(): List<Hometown> {
        return listOf(
            Hometown("서울", "seoul"),
            Hometown("경기", "gyeonggi"),
            Hometown("인천", "incheon"),
        )
    }
}
