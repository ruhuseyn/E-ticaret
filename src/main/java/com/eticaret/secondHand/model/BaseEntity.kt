package com.eticaret.secondHand.model

import java.time.LocalDate

class BaseEntity(
        val createdDate : LocalDate? = null,
        val updatedDate : LocalDate? = null
) {
}