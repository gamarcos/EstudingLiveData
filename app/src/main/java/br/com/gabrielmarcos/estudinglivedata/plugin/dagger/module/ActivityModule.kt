package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module

import br.com.gabrielmarcos.estudinglivedata.base.view.base.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}