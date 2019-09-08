package br.com.gabrielmarcos.estudinglivedata.feature.models

data class Account (
    val agency: String?,
    val accountNumber: String?,
    val checkingAccount: CheckingAccount?,
    val savingAccount: SavingAccount?,
    val limitTransationPerDay: Double?
)
data class CheckingAccount(val amount: Double?)

data class SavingAccount(val amount: Double?)