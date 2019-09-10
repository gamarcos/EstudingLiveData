package br.com.gabrielmarcos.estudinglivedata.feature.models

import java.io.Serializable

data class Client(
    val id: String?,
    val name: String?,
    val email: String?,
    val accountId: String?,
    val image: String?,
    val contacts: List<Contact>?
): Serializable