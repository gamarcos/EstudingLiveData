package br.com.gabrielmarcos.estudinglivedata.feature.transaction.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.gabrielmarcos.estudinglivedata.R
import br.com.gabrielmarcos.estudinglivedata.base.view.base.BaseFragment
import br.com.gabrielmarcos.estudinglivedata.base.view.util.MoneyUtils
import br.com.gabrielmarcos.estudinglivedata.base.view.util.MoneyWatcher
import br.com.gabrielmarcos.estudinglivedata.base.view.util.loadImageIntoView
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.gateway.TransactionViewModel
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionFragment: BaseFragment<TransactionViewModel>(), TextWatcher, DefaultAmountValuesAdapter.ValueClickedListener {

    private val contact: Contact? by lazy { arguments?.let {
            TransactionFragmentArgs.fromBundle(it).contactToTransfer
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getReceiverData()
        setUpDefaultValues()
        transactionAmountValue.addTextChangedListener(MoneyWatcher(transactionAmountValue))
        transactionSendAmountButton.setOnClickListener {  }
    }

    private fun setUpView(account: Account) {
        loadImageIntoView(view!!, transactionReceiverPhotoView, contact?.image?:"")

        transactionReceiverNameText.text = contact?.name?:""
        transactionReceiverAccountText.text = account.accountNumber
    }

    private fun setUpDefaultValues() {
        transactionAmountDefaultValueList.apply {
            adapter = DefaultAmountValuesAdapter(listOf("+ R$ 1", "+ R$ 10", "+ R$ 50"), this@TransactionFragment, context)
        }
    }

    override fun handleSuccess(value: Any?) {
        super.handleSuccess(value)
        when (value) {
            is Account -> setUpView(value)
        }
    }

    override fun getViewModel(): Class<TransactionViewModel> {
        return TransactionViewModel::class.java
    }

    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onValueClicked(value: String) {
        transactionAmountValue.setText(value)
    }
}