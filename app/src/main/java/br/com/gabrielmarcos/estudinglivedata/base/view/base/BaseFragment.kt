package br.com.gabrielmarcos.estudinglivedata.base.view.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.gabrielmarcos.estudinglivedata.base.business.exception.HttpException
import br.com.gabrielmarcos.estudinglivedata.base.business.exception.InternetConnectionException
import br.com.gabrielmarcos.estudinglivedata.base.gateway.BaseViewModel
import br.com.gabrielmarcos.estudinglivedata.base.gateway.ViewState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

//TODO to use databind use VIewDataBind into Constructor
abstract class BaseFragment<V: BaseViewModel>: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: V

    private var loading: View? = null

    protected abstract fun getViewModel(): Class<V>

    //TODO To use dataBind

    // @LayoutRes
    // protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        observeViewModel()
    }

    protected open fun showLoading() {
        loading?.visibility = View.VISIBLE
    }

    protected open fun hideLoading() {
        try {
            loading?.visibility = View.GONE
        } catch(e: Exception) {
            Log.e("ERROR", "error loading", e)
        }
    }

    //Todo Criar lives customs

    protected open fun observeViewModel() {
        observeAllChannels()
    }

    protected fun observeAllChannels() {
        viewModel.getChannels().forEach { observeChannel(it) }
    }

    protected fun observeChannel(channelName: String) {
        viewModel.observe(channelName,this, Observer { v -> handleResponse(v) })
    }

    protected open fun handleResponse(state: ViewState) {
        if(!state.handled)  {
            hideLoading()
            state.handled = true
            if (state.isError()) {
                handleThrowable(state.output.error)
            } else {
                handleSuccess(state.output.value)
            }
        }
    }

    private fun handleThrowable(error: Throwable?) {
        when(error) {
            is HttpException -> handleHttpError(error)
            is InternetConnectionException -> handleHttpError(error.cause.toString())
            else -> handleError(error)
        }
    }

    protected open fun handleHttpError(error: HttpException) {
        Toast.makeText(context, "Algo deu errado! Erro HTTP: ${error.code}", Toast.LENGTH_LONG).show()
    }

    protected open fun handleHttpError(error: String?) {
        Toast.makeText(context, "Algo deu errado! Erro HTTP: ${error}", Toast.LENGTH_LONG).show()
    }

    protected open fun handleError(error: Throwable?) {}

    protected open fun handleSuccess(value: Any?) {}
}