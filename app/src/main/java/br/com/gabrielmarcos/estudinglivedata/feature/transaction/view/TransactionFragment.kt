package br.com.gabrielmarcos.estudinglivedata.feature.transaction.view

import br.com.gabrielmarcos.estudinglivedata.base.view.base.BaseFragment
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.gateway.TransactionViewModel

class TransactionFragment: BaseFragment<TransactionViewModel>() {

    override fun getViewModel(): Class<TransactionViewModel> {
        return TransactionViewModel::class.java
    }
}