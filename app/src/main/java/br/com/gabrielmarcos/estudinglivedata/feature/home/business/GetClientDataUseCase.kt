package br.com.gabrielmarcos.estudinglivedata.feature.home.business

import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Output
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.UseCase
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import javax.inject.Inject

class GetClientDataUseCase @Inject constructor(private val repository: HomeRepository): UseCase<Nothing?, ClientData>() {

    override fun execute(param: Nothing?): Output<ClientData> {
        return Output.success(converterToClient())
    }

    private fun converterToClient(): ClientData  {
        val client = repository.getClientData()
        val account = repository.getAccountData(client?.accountId)
        return ClientData(client, account)
    }
}