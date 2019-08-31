package br.com.gabrielmarcos.estudinglivedata.plugin.main

import android.app.Application
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication: Application(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}