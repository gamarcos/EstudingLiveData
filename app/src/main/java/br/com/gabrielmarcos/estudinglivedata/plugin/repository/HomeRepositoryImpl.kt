package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import br.com.gabrielmarcos.estudinglivedata.plugin.network.executeCall
import br.com.gabrielmarcos.estudinglivedata.plugin.network.getAPI
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(var context: Context) : BaseRepository(), HomeRepository {

    private fun getClientDAO(): ContactsDAO {
        return ContactsDAO(context)
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
        return getClientDAO().getRecentContactsSaved()
    }

    override fun setRecentsContactsTransfer(contacts: Contact) {
        getClientDAO().saveRecentContact(contacts)
    }
}