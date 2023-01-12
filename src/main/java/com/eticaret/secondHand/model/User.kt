package com.eticaret.secondHand.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class User(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?=null,
        val mail: String,
        val firstName: String,
        val lastName: String,
        val middleName: String
){

}