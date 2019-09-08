package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import br.com.gabrielmarcos.estudinglivedata.plugin.network.executeCall
import br.com.gabrielmarcos.estudinglivedata.plugin.network.getAPI
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(): BaseRepository(), HomeRepository {

    override fun getClientData(): Client? {
        val call = getAPI().getClient()
        return executeCall(call)?.body()?.let { it }

    }

    override fun getAccountData(accountId: String?): Account? {
        val call = getAPI().getAccount(accountId)
        return executeCall(call)?.body()?.let { it }
    }
}