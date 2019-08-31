package br.com.gabrielmarcos.estudinglivedata.base.business.exception

class HttpException(val code: Int, message: String = ""): RuntimeException(message)

class InternetConnectionException(message: String? = null): RuntimeException(message)