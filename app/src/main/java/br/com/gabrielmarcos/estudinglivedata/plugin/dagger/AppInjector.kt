package br.com.gabrielmarcos.estudinglivedata.plugin.dagger

import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.component.DaggerAppComponent
import br.com.gabrielmarcos.estudinglivedata.plugin.main.BaseApplication

object AppInjector {
    @JvmStatic
    fun init(baseApplication: BaseApplication) {
        DaggerAppComponent.builder().application(baseApplication).build()
            .injectApplication(baseApplication)
    }
}
