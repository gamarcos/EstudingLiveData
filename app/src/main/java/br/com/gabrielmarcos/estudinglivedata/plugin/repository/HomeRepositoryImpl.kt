package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import br.com.gabrielmarcos.estudinglivedata.plugin.network.executeCall
import br.com.gabrielmarcos.estudinglivedata.plugin.network.getAPI
import br.com.gabrielmarcos.estudinglivedata.plugin.repository.data.ClientDAO
import br.com.gabrielmarcos.estudinglivedata.plugin.repository.data.ContactsDAO
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(var context: Context) : BaseRepository(), HomeRepository {

    private fun getContactDAO(): ContactsDAO {
        return ContactsDAO(context)
    }

    private fun getClientDAO(): ClientDAO {
        return ClientDAO(context)
    }

    override fun getClientData(): Client? {
        val call = getAPI().getClient()
        return executeCall(call)?.body()?.let { it }
    }

    override fun getAccountData(accountId: String?): Account? {
        val call = getAPI().getAccount(accountId)
        return executeCall(call)?.body()?.let { it }
    }

    override fun getRecentsContactsTransfer(): List<Contact> {
        return getContactDAO().getRecentContactsSaved()
    }

    override fun setRecentsContactsTransfer(contacts: Contact) {
        getContactDAO().saveRecentContact(contacts)
    }

    override fun saveClientData(clientData: ClientData) {
        getClientDAO().saveClientData(clientData)
    }

    override fun getLocalClientData(): ClientData {
        return getClientDAO().getClientData()
    }
}