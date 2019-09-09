package br.com.gabrielmarcos.estudinglivedata.feature.home.gateway

import br.com.gabrielmarcos.estudinglivedata.base.gateway.BaseViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.GetClientDataUseCase
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private var getClientDataUseCase: GetClientDataUseCase,
    private var homeRepository: HomeRepository
): BaseViewModel() {

    override fun declareChannels() {
        availableChannels.add("CLIENT_DATA")
    }

    fun getClientData() {
        request(channelName = "CLIENT_DATA", useCase = getClientDataUseCase)
    }

    fun getRecentsContactsTransfer() : List<Contact> {
        return homeRepository.getRecentsContactsTransfer()
    }

    fun setRecentsContactsTransfer(contact: Contact) {
        homeRepository.setRecentsContactsTransfer(contact)
    }
}