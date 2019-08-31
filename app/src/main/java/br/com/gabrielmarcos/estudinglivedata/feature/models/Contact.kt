package br.com.gabrielmarcos.estudinglivedata.feature.models

import java.io.Serializable

data class Contact (
    private val id: String?,
    private val name: String?,
    private val email: String?,
    private val accountId: String?
): Serializable