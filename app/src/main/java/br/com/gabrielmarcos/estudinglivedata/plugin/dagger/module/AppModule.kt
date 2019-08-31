package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.plugin.main.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: BaseApplication): Context {
        return application.applicationContext
    }
}
