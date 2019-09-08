package br.com.gabrielmarcos.estudinglivedata.feature.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmarcos.estudinglivedata.R
import br.com.gabrielmarcos.estudinglivedata.base.view.util.loadImageIntoView
import br.com.gabrielmarcos.estudinglivedata.feature.home.view.ContactsAdapter.ViewHolder.*
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import kotlinx.android.synthetic.main.adapter_contacts.view.*

class ContactsAdapter(private val contacts: List<Contact>? = emptyList(),
    private val listener: ContactListener? = null,
    private val context: Context?) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_contacts, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return contacts?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        contacts?.get(position)?.let { holder.bind(it) }
    }

    //TODO Criar um viewHolder generico
    class ViewHolder(itemView: View, private val listener: ContactListener?) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact) {
            loadImageIntoView(itemView, itemView.contactsImageView, contact.image?:"")
            itemView.contactsNameTextView.text = contact.name?:""
            itemView.adapterContactContent.setOnClickListener {
                listener?.onClickListener(contact)
            }
        }

        interface ContactListener {
            fun onClickListener(contact: Contact)
        }

    }
}