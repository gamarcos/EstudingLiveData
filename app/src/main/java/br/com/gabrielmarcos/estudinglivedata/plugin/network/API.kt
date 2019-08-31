package br.com.gabrielmarcos.estudinglivedata.plugin.network

import retrofit2.Call
import br.com.gabrielmarcos.estudinglivedata.BuildConfig
import br.com.gabrielmarcos.estudinglivedata.base.business.exception.HttpException
import br.com.gabrielmarcos.estudinglivedata.base.business.exception.InternetConnectionException
import br.com.gabrielmarcos.estudinglivedata.feature.models.Account
import br.com.gabrielmarcos.estudinglivedata.feature.models.Client
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.io.IOException
import java.util.concurrent.TimeUnit

internal interface API {
    @GET("client")
    fun getClient(): Call<Client>

    @POST("account")
    fun getAccount(@Header("accountId") accountId: String?): Call<Account>
}

internal fun getAPI(baseUrl: String = "http://demo5724941.mockable.io/"): API {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    val client = OkHttpClient.Builder().let {
        it.addInterceptor(interceptor)
        it.connectTimeout(5, TimeUnit.SECONDS)
        it.readTimeout(5, TimeUnit.SECONDS)
        it.writeTimeout(5, TimeUnit.SECONDS)
        it.build()
    }

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(API::class.java)
}

internal fun <P> executeCall(call: Call<P>): Response<P>? {
    try {
        val response = call.execute()
        if(!response.isSuccessful) throw HttpException(response.code(), response.message())
        return response
    } catch(e: IOException) {
        throw InternetConnectionException()
    }
}