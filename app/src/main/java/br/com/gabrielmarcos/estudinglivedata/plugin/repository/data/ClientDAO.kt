package br.com.gabrielmarcos.estudinglivedata.plugin.repository.data

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ClientDAO @Inject constructor(context: Context): AbstractDAO(context) {

    companion object {
        private const val KEY_SAVE_ACCOUNT_DATA = "KEY_SAVE_ACCOUNT_DATA"
    }

    fun saveClientData(clientData: ClientData) {
        val clientJsonData = parseAccountDataToJSON(clientData)
        sharedPreferences?.edit()?.putString(KEY_SAVE_ACCOUNT_DATA, clientJsonData)?.apply()
    }

    //TODO Mover para AbstractDAO com Any
    private fun parseAccountDataToJSON(clientData: ClientData): String {
        return Gson().toJson(clientData)
    }

    fun getClientData(): ClientData {
        val gson = GsonBuilder().create()
        val clientData = sharedPreferences?.getString(KEY_SAVE_ACCOUNT_DATA, "{}")
        return gson.fromJson(clientData, ClientData::class.java)
    }
}