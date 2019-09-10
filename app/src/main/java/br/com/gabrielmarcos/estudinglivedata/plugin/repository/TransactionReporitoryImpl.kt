package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.business.TransactionReporitory
import br.com.gabrielmarcos.estudinglivedata.plugin.network.executeCall
import br.com.gabrielmarcos.estudinglivedata.plugin.network.getAPI
import javax.inject.Inject

class TransactionReporitoryImpl @Inject constructor(var context: Context) : BaseRepository(),
    TransactionReporitory {

    override fun getAccount(accountId: String): Account? {
        val call = getAPI().getAccount(accountId)
        return executeCall(call)?.body()?.let { it }
    }

    override fun sendAmount(amount: Double, contact: Contact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}