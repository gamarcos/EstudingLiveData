package br.com.gabrielmarcos.estudinglivedata.feature.home.gateway

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabrielmarcos.estudinglivedata.base.gateway.BaseViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.GetClientDataUseCase
import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    var clientMutableLiveData = MutableLiveData<Client>()

    fun getClientData() {
        clientMutableLiveData.value = repository.getClientData()
    }
}