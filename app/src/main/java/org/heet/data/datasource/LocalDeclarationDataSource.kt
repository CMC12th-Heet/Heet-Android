package org.heet.data.datasource

import org.heet.data.local.DeclarationItem

class LocalDeclarationDataSource {
    fun loadDeclarations(): List<DeclarationItem> {
        return listOf(
            DeclarationItem("불법 또는 규제 상품 판매", false),
            DeclarationItem("지적재산권 침해", false),
            DeclarationItem("나체 이미지 또는 성적 행위", false),
            DeclarationItem("사기 또는 거짓 정보", false),
            DeclarationItem("폭력적인 또는 공격적 내용", false),
            DeclarationItem("자해 또는 잔인한 내용", false),
            DeclarationItem("기타 사용", false)
        )
    }
}
