package br.com.gabrielmarcos.estudinglivedata.feature.home.view

import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmarcos.estudinglivedata.R
import br.com.gabrielmarcos.estudinglivedata.base.view.base.BaseFragment
import br.com.gabrielmarcos.estudinglivedata.base.view.util.hideKeyboardFrom
import br.com.gabrielmarcos.estudinglivedata.base.view.util.loadImageIntoView
import br.com.gabrielmarcos.estudinglivedata.base.view.util.showKeyboard
import br.com.gabrielmarcos.estudinglivedata.feature.home.gateway.HomeViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.home.view.ContactsAdapter.ViewHolder.ContactListener
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import br.com.gabrielmarcos.estudinglivedata.feature.models.Contact
import kotlinx.android.synthetic.main.content_custom_searchbar.*
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

        searchBarFinderText.setOnTouchListener { _, _ ->
            setUpShowSearchBar()
            false
        }

        searchBarFinderText.addTextChangedListener(getContactSearch())
        searchBarFindView.setOnClickListener { setUpShowSearchBar() }
        searchBarCloseView.setOnClickListener { setUpHideSearchBar() }
    }

    private fun setUpShowSearchBar() {
        if (searchBarFindView.isVisible) {
            searchBarFinderText.isFocusableInTouchMode = true
            searchBarFinderText.requestFocus()
            showKeyboard(searchBarFinderText, context)
            searchBarFindView.visibility = View.GONE
            searchBarCloseView.visibility = View.VISIBLE
        }
    }

    private fun setUpHideSearchBar() {
        if (searchBarCloseView.isVisible) {
            searchBarFindView.visibility = View.VISIBLE
            searchBarCloseView.visibility = View.GONE
            setUpCloseSearchBar()
        }
    }

    private fun setUpCloseSearchBar() {
        hideKeyboardFrom(context, view)
        searchBarFinderText.clearFocus()
        searchBarFinderText.text.clear()
    }

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
        setUpHeaderView(clientData)
        setUpRecyclerContacts(homeRecentList, clientData?.getContacts())
        setUpRecyclerContacts(homeContactsList, clientData?.getContacts())
        setUpContacts()
        hideShimmer()
    }

    private fun setUpHeaderView(clientData: ClientData?) {
        headerClientNameText.text = clientData?.getClientName()
        setUpAmount(clientData)
        loadImageIntoView(headerContent, headerClientImageVView, "https://scontent.fcgh28-1.fna.fbcdn.net/v/t1.0-9/51996574_2111850205566006_53001580059820032_n.jpg?_nc_cat=103&_nc_oc=AQkPBT6xQyijdltfBzuVdUKGfKwfLm0sMYYuguttHulL0JQ8n0wMVYaLs7e0JXIIB_w&_nc_ht=scontent.fcgh28-1.fna&oh=309f57cb126cfeae8060571a9cccaa69&oe=5E0DCEA9")
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

    private fun showSearchContacts(filter: String) {
        setUpRecyclerContacts(homeContactsList, filterContacts?.filter { it.name?.run{ contains(filter) }?:false} )

    }

    //TODO jogar para a viewmodel
    private fun orderListByName(contacts: List<Contact>?): List<Contact>? {
       return contacts?.sortedBy { it.name }
    }

    override fun onClickListener(contact: Contact) {
        print(contact)
    }

    private fun setUpContacts() {

    }

    private fun hideShimmer() {
        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
    }

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

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