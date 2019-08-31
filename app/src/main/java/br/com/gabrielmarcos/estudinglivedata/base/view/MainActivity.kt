package br.com.gabrielmarcos.estudinglivedata.base.view

import android.os.Bundle
import br.com.gabrielmarcos.estudinglivedata.R
import dagger.android.AndroidInjector

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
