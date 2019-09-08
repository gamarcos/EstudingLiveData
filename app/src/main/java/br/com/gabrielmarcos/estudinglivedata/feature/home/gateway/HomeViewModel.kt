package br.com.gabrielmarcos.estudinglivedata.feature.home.gateway

import br.com.gabrielmarcos.estudinglivedata.base.gateway.BaseViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.GetClientDataUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private var getClientDataUseCase: GetClientDataUseCase
): BaseViewModel() {

    override fun declareChannels() {
        availableChannels.add("CLIENT_DATA")
    }

    fun getClientData() {
        request(channelName = "CLIENT_DATA", useCase = getClientDataUseCase)
    }
}