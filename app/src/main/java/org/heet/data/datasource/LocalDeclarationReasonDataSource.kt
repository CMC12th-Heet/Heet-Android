package org.heet.data.datasource

import org.heet.R
import org.heet.data.local.DeclarationReasonItem

class LocalDeclarationReasonDataSource {

    fun loadDeclarationReasons(): List<DeclarationReasonItem> {
        return listOf(
            DeclarationReasonItem(
                R.string.local_declaration_reason_selling_illegal,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_intellectual_property_infringement,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_nudity_images_or_sexual_behavior,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_fraud_or_false_information,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_violent_or_offensive_content,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_self_harm_or_cruelty,
                false,
            ),
            DeclarationReasonItem(
                R.string.local_declaration_reason_etc,
                false,
            ),
        )
    }
}
