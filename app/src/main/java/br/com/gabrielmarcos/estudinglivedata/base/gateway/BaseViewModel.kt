package br.com.gabrielmarcos.estudinglivedata.base.gateway

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.CompositeJobDisposable
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.Output
import br.com.gabrielmarcos.estudinglivedata.base.business.interactor.UseCase
import kotlinx.coroutines.Job

open class ViewState(val output: Output<*>, var handled: Boolean = false) {
    fun isError(): Boolean = output.isError()
    fun isEmpty(): Boolean = output.isEmpty()
    fun isSuccess(): Boolean = output.isSuccess()
}

abstract class BaseViewModel: ViewModel() {
    private val channels: MutableMap<String, MutableLiveData<ViewState>> = mutableMapOf()
    protected val availableChannels = mutableListOf<String>()
    protected val compositeJobDisposable = CompositeJobDisposable()

    init {
        onCreate()
    }

    private fun onCreate() {
        declareChannels()
    }

    protected abstract fun declareChannels()

    fun observe(channelName: String, owner: LifecycleOwner, listener: Observer<in ViewState>) {
        createChannelIfNeeded(channelName)
        channels[channelName]?.observe(owner, listener)
    }

    fun getChannels(): List<String> {
        return availableChannels
    }

    fun stopRequests() {
        compositeJobDisposable.cancel()
    }

    protected open fun <P,R> request(channelName: String, useCase: UseCase<P, R>, param: P? = null): Job? {
        val job = useCase.invoke(param) {postValue(channelName, it)}
        compositeJobDisposable.add(job)
        return job
    }

    protected open fun postValue(channelName: String, output: Output<*>) {
        val channel = channels[channelName]
        val viewState = ViewState(output)
        channel?.postValue(viewState)
    }

    private fun createChannelIfNeeded(channelName: String) {
        if(availableChannels.contains(channelName) && channels[channelName] == null) {
            channels[channelName] = MutableLiveData()
        }
    }
}