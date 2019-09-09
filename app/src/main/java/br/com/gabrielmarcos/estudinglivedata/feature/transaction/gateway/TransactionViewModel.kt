package br.com.gabrielmarcos.estudinglivedata.feature.transaction.gateway

import br.com.gabrielmarcos.estudinglivedata.base.gateway.BaseViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.business.SendAmountUseCase
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val sendAmountUseCase: SendAmountUseCase
): BaseViewModel() {

    override fun declareChannels() {
        availableChannels.addAll(listOf("receiver", "transaction"))
    }

    fun getReceiverData() {
        request("receiver", sendAmountUseCase)
    }
}