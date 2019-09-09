package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module

import br.com.gabrielmarcos.estudinglivedata.feature.home.view.HomeFragment
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.view.TransactionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeTransactionFragment(): TransactionFragment
}