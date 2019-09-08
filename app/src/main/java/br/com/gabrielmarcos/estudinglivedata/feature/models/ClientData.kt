package br.com.gabrielmarcos.estudinglivedata.feature.models

data class ClientData(val client: Client? = null, val account: Account? = null) {

    fun getSavingAmount(): Double? {
        return account?.savingAccount?.run { amount }
    }

    fun getCheckingAmount(): Double? {
        return account?.checkingAccount?.run { amount }
    }

    fun getClientName() : String? {
        return client?.name
    }

    fun getContacts() : List<Contact>? {
        return client?.contacts
    }
}