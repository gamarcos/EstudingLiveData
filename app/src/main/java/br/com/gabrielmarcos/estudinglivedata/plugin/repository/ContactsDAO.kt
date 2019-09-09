package br.com.gabrielmarcos.estudinglivedata.plugin.repository

import android.annotation.SuppressLint
import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ContactsDAO @Inject constructor(context: Context): AbstractDAO(context) {

    companion object {
        private const val KEY_SAVE_CONTACT = "KEY_SAVE_CONTACT"
    }

    @SuppressLint("CommitPrefEdits")
    fun saveRecentContact(contact: Contact) {
        val contacts = Gson().toJson(hasContactsSaved(contact)?:"")
        sharedPreferences?.edit()?.putString(KEY_SAVE_CONTACT, contacts)?.apply()
    }

    private fun hasContactsSaved(contact: Contact) : List<Contact>? {
        var contactsList: List<Contact>? = null
        contactsList = ArrayList()
        if (getRecentContactsSaved().isNotEmpty()) {
            getRecentContactsSaved()
                .filter { it != contact }
                .map { contactsList.add(contact) }
        } else {
            contactsList.add(contact)
        }
        return contactsList.sortedBy { it.name }
    }

    fun getRecentContactsSaved(): List<Contact> {
        val gson = GsonBuilder().create()
        val contacts = sharedPreferences?.getString(KEY_SAVE_CONTACT, "[]")
        //TODO MUITO FEIO CARA
        return gson.fromJson(contacts, Array<Contact>::class.java).toList()
    }
}