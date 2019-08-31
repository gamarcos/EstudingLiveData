package br.com.gabrielmarcos.estudinglivedata.base.business.interactor

import kotlinx.coroutines.Job


abstract class AbstractUseCase<in P, R>: Interactor<P, R> {
    lateinit var callback: (R)->Unit

    fun fireAndForget(param: P? = null) {
        process(param, {})
    }

    fun process(param: P? = null, callback: (R)->Unit) {
        try {
            this.callback = callback
            handle(param)
        } catch(error: Throwable) {
            onError(error)
        }
    }

    protected open fun handle(param: P?) {
        takeIf { guard(param) }?.execute(param).also { onSuccess(it!!) }
    }

    internal open fun onSuccess(output: R) {
        callback(output)
    }

    internal abstract fun onError(error: Throwable)
}


abstract class UseCase<in P, R>: AbstractUseCase<P, Output<R>>() {
    override fun onError(error: Throwable) {
        callback(Output.failure(error))
    }

    fun invoke(param: P? = null, callback: (Output<R>)->Unit): Job? {
        return UseCaseInvoker(this).dispatch(param, callback)
    }
}