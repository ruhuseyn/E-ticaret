package com.eticaret.secondHand.dto

data class UpdateUserRequest(
        val firstName: String,
        val lastName: String,
        val middleName: String
) {
}