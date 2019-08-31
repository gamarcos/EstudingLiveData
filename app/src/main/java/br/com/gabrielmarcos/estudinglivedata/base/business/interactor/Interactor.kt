package br.com.gabrielmarcos.estudinglivedata.base.business.interactor

interface Interactor<in P, R> {
    fun guard(param: P? = null): Boolean = true
    fun execute(param: P? = null): R
}