package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module

import br.com.gabrielmarcos.estudinglivedata.feature.home.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}