package br.com.gabrielmarcos.estudinglivedata.feature.home.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmarcos.estudinglivedata.R
import br.com.gabrielmarcos.estudinglivedata.base.view.base.BaseFragment
import br.com.gabrielmarcos.estudinglivedata.base.view.util.loadImageIntoView
import br.com.gabrielmarcos.estudinglivedata.feature.home.gateway.HomeViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.home.view.ContactsAdapter.ViewHolder.ContactListener
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.view.TransactionFragmentArgs
import kotlinx.android.synthetic.main.content_header_profile.*
import kotlinx.android.synthetic.main.content_header_profile.headerContent
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment<HomeViewModel>(),
    ContactListener {

    private val filterContacts: List<Contact>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getClientData()
        // setUpSearchBar()
    }

    // TODO PREPARAR BUSCA
    // private fun setUpSearchBar() {
    //     searchBarFinderText.setOnTouchListener { _, _ ->
    //         setUpShowSearchBar()
    //         showKeyboard(searchBarFinderText, context)
    //         false
    //     }
    //
    //     searchBarFinderText.addTextChangedListener(getContactSearch())
    //     searchBarFindView.setOnClickListener { setUpShowSearchBar() }
    //     searchBarCloseView.setOnClickListener { setUpHideSearchBar() }
    // }
    //
    //
    // private fun setUpShowSearchBar() {
    //     if (searchBarFindView.isVisible) {
    //         searchBarFinderText.isFocusableInTouchMode = true
    //         searchBarFinderText.requestFocus()
    //         searchBarFindView.visibility = View.GONE
    //         searchBarCloseView.visibility = View.VISIBLE
    //     }
    // }
    //
    // private fun setUpHideSearchBar() {
    //     if (searchBarCloseView.isVisible) {
    //         searchBarFindView.visibility = View.VISIBLE
    //         searchBarCloseView.visibility = View.GONE
    //         setUpCloseSearchBar()
    //     }
    // }
    //
    // private fun setUpCloseSearchBar() {
    //     hideKeyboardFrom(context, view)
    //     searchBarFinderText.clearFocus()
    //     searchBarFinderText.text.clear()
    // }

    override fun handleSuccess(value: Any?) {
        super.handleSuccess(value)
        when (value) {
            is ClientData -> setUpHomeView(value)
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainer.startShimmer()
    }

    private fun setUpHomeView(clientData: ClientData?) {
        // clientData?.getContacts()?.get(0)?.let { viewModel.setRecentsContactsTransfer(it) }
        setUpHeaderView(clientData)
        setUpRecyclerContacts(homeRecentList, viewModel.getRecentsContactsTransfer())
        setUpRecyclerContacts(homeContactsList, clientData?.getContacts())
        hideShimmer()
    }

    private fun setUpHeaderView(clientData: ClientData?) {
        headerClientNameText.text = clientData?.getClientName()
        setUpAmount(clientData)
        loadImageIntoView(headerContent, headerClientImageVView, clientData?.getClientPhoto()?:"")
    }

    //TODO jogar para a viewmodel
    private fun setUpAmount(clientData: ClientData?) {
        val amounts = listOf(
            Pair(R.drawable.ic_checking_money_94dp, clientData?.getCheckingAmount().toString()),
            Pair(R.drawable.ic_saving_money_94dp, clientData?.getSavingAmount().toString())
        )

        headerAmountList.apply {
            adapter = AmountAdapter(amounts, context)
        }
    }

    private fun setUpRecyclerContacts(recyclerView: RecyclerView, contacts: List<Contact>?) {
        recyclerView.apply {
            adapter = ContactsAdapter(orderListByName(contacts), this@HomeFragment, context)
        }
    }

    //TODO Parte da searchbar
    private fun showSearchContacts(filter: String) {
        setUpRecyclerContacts(homeContactsList, filterContacts?.filter {
            it.name?.run {
                contains(filter) }?:false
        })
    }

    //TODO jogar para a viewmodel
    private fun orderListByName(contacts: List<Contact>?): List<Contact>? {
       return contacts?.sortedBy { it.name }
    }

    override fun onClickListener(contact: Contact) {
        val args = TransactionFragmentArgs.Builder().setContactToTransfer(contact).build().toBundle()
        NavHostFragment.findNavController(this).navigate(R.id.action_home_to_transaction, args)
    }

    private fun hideShimmer() {
        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
    }

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    //TODO Parte da searchbar
    private fun getContactSearch(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                showSearchContacts(editable.toString())
            }
        }
    }
}