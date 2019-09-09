package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gabrielmarcos.estudinglivedata.base.gateway.ViewModelFactory
import br.com.gabrielmarcos.estudinglivedata.feature.home.gateway.HomeViewModel
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.gateway.TransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindTransactionViewModel(viewModel: TransactionViewModel): ViewModel

    @Binds
    abstract fun bindViewModel(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
