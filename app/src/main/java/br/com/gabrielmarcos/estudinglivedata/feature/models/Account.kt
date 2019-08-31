package br.com.gabrielmarcos.estudinglivedata.feature.models

data class Account (
    private val agency: String?,
    private val account: String?,
    private val checkingAccount: CheckingAccount?,
    private val savingAccount: SavingAccount?,
    private val limitTransationPerDay: Double?
)

data class CheckingAccount(private val amount: Double?)

data class SavingAccount(private val amount: Double?)