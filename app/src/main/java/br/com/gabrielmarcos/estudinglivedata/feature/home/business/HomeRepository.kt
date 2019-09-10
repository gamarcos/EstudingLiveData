package br.com.gabrielmarcos.estudinglivedata.feature.home.business

import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact

interface HomeRepository {
    fun getAccountData(accountId: String?): Account?
    fun getClientData() : Client?
    fun getRecentsContactsTransfer() : List<Contact>
    fun setRecentsContactsTransfer(contact: Contact)
    fun saveClientData(clientData: ClientData)
    fun getLocalClientData() : ClientData
}