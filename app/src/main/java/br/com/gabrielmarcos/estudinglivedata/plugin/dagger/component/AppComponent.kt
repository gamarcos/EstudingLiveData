package br.com.gabrielmarcos.estudinglivedata.plugin.dagger.component

import android.app.Application
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module.ActivityModule
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module.AppModule
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module.FragmentModule
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.module.RepositoryModule
import br.com.gabrielmarcos.estudinglivedata.plugin.dagger.viewmodel.ViewModelModule
import br.com.gabrielmarcos.estudinglivedata.plugin.main.BaseApplication
import br.com.gabrielmarcos.estudinglivedata.plugin.repository.data.ContactsDAO
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun injectApplication(app: BaseApplication)

    fun contactsDAO(): ContactsDAO
}