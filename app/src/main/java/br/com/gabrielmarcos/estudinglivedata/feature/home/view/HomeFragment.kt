package br.com.gabrielmarcos.estudinglivedata.feature.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.gabrielmarcos.estudinglivedata.R
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Output
import br.com.gabrielmarcos.estudinglivedata.base.gateway.ViewState
import br.com.gabrielmarcos.estudinglivedata.base.view.BaseFragment
import br.com.gabrielmarcos.estudinglivedata.feature.home.gateway.HomeViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.models.ClientData
import javax.inject.Inject

class HomeFragment: BaseFragment<HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        viewModel.run {
            getClientData()
        }
    }


    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}