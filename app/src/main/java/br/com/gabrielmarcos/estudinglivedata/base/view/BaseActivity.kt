package br.com.gabrielmarcos.estudinglivedata.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    @Inject
    internal lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    //TODO To use dataBind
    // @LayoutRes
    // protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentAndroidInjector
    }
}