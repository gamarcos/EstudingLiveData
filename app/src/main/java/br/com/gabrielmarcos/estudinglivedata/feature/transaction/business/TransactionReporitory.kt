package br.com.gabrielmarcos.estudinglivedata.feature.transaction.business

import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact

interface TransactionReporitory {
    fun sendAmount(amount: Double, contact: Contact)
}