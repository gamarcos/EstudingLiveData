package br.com.gabrielmarcos.estudinglivedata.base.view.base

import android.os.Bundle
import br.com.gabrielmarcos.estudinglivedata.R

class MainActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
