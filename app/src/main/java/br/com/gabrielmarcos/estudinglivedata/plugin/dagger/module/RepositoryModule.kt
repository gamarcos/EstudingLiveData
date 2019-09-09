package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module

import br.com.gabrielmarcos.estudinglivedata.feature.home.business.HomeRepository
import br.com.gabrielmarcos.estudinglivedata.feature.transaction.business.TransactionReporitory
import br.com.gabrielmarcos.estudinglivedata.plugin.repository.HomeRepositoryImpl
import br.com.gabrielmarcos.estudinglivedata.plugin.repository.TransactionReporitoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindTransactionReporitory(homeRepository: TransactionReporitoryImpl): TransactionReporitory
}
