package br.com.gabrielmarcos.estudinglivedata.base.view.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MoneyWatcher(private val valueEditText: EditText) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        valueEditText.removeTextChangedListener(this)
        val string = MoneyUtils().formatMoneyToString(s.toString())
        valueEditText.setText(string)
        if (string != null) {
            valueEditText.setSelection(string.length)
        }
        valueEditText.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable) {}

}