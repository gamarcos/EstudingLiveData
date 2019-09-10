package br.com.gabrielmarcos.estudinglivedata.feature.transaction.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmarcos.estudinglivedata.R
import kotlinx.android.synthetic.main.adapter_default_amount_value.view.*

class DefaultAmountValuesAdapter(private val defaultValues: List<String>,
    private val valueClickedListener: ValueClickedListener,
    private val context: Context): RecyclerView.Adapter<DefaultAmountValuesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_default_amount_value, parent, false), valueClickedListener)
    }

    override fun getItemCount(): Int {
        return  defaultValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(defaultValues[position])
    }

    class ViewHolder(itemView: View, private val listener: ValueClickedListener): RecyclerView.ViewHolder(itemView) {
        fun bindView(defaultValue: String) {
            itemView.defaultTransactionValueText.text = defaultValue
            itemView.defaultTransactionValueContent.setOnClickListener { listener.onValueClicked(defaultValue) }
        }
    }

    interface ValueClickedListener {
        fun onValueClicked(value: String)
    }
}