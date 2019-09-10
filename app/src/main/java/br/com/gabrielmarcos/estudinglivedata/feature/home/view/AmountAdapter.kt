package br.com.gabrielmarcos.estudinglivedata.feature.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmarcos.estudinglivedata.R
import kotlinx.android.synthetic.main.adapter_amount.view.*

class AmountAdapter(private val amounts: List<Pair<Int, String>>, private val context: Context) : RecyclerView.Adapter<AmountAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_amount,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return amounts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(amounts[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(amount: Pair<Int, String>) {
            itemView.headerAmountAccountView.setImageResource(amount.first)
            itemView.headerAmountAccountText.text = amount.second
        }
    }
}