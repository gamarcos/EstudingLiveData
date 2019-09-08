package br.com.gabrielmarcos.estudinglivedata.feature.models

import java.io.Serializable

data class Contact (
    val id: String?,
    val name: String?,
    val email: String?,
    val accountId: String?,
    val image: String?
): Serializable