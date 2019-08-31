package br.com.gabrielmarcos.estudinglivedata.feature.home.business

import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client

interface HomeRepository {
    fun getAccountData(accountId: String?): Account?
    fun getClientData() : Client?
}