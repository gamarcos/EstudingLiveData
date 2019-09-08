package br.com.gabrielmarcos.estudinglivedata.base.business.interactor

import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Status.ERROR
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Status.LOADING
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Status.SUCCESS

class Resource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null) {

    companion object {
        @JvmStatic
        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data)
        }
        @JvmStatic
        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(ERROR, data, msg)
        }
        @JvmStatic
        fun <T> loading(data: T): Resource<T> {
            return Resource(LOADING, data)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}