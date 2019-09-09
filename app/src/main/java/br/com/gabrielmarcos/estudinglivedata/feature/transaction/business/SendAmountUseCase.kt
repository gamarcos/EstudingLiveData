package br.com.gabrielmarcos.estudinglivedata.feature.transaction.business

import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Output
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.UseCase
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import javax.inject.Inject

class SendAmountUseCase @Inject constructor(private val repository: TransactionReporitory): UseCase<Pair<Double, Contact>, Nothing?>() {
    override fun execute(param: Pair<Double, Contact>?): Output<Nothing?> {
        param?.first?.let { repository.sendAmount(it, param.second) }
        return Output.success()
    }
}