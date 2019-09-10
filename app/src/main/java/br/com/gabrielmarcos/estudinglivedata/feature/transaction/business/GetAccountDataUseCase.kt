package br.com.gabrielmarcos.estudinglivedata.feature.transaction.business

import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Output
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.UseCase
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import javax.inject.Inject

class GetAccountDataUseCase @Inject constructor(
    private val transactionReporitory: TransactionReporitory
): UseCase<String, Account?>() {
    override fun execute(param: String?): Output<Account?> {
        return Output.success( transactionReporitory.getAccount(param?:""))
    }
}