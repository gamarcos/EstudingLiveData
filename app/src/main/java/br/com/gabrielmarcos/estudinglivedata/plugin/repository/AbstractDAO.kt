package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import android.content.Context
import android.content.SharedPreferences

abstract class AbstractDAO(context: Context) {

    private val PREF_NAME = "LiveDataPreferences"

    var sharedPreferences: SharedPreferences?

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

}