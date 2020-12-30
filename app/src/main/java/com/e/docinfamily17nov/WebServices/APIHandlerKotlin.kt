package com.e.docinfamily17nov.WebServices

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIHandlerKotlin {
    private var okhttpclient: OkHttpClient? = null
    private var interceptor: HttpLoggingInterceptor? = null
    private var retrofit: Retrofit? = null
    var handlerr : APICallMethodsKotlin? = null

    init {
        interceptor = HttpLoggingInterceptor()
        interceptor?.level = HttpLoggingInterceptor.Level.BODY
        okhttpclient = OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit=Retrofit.Builder()
            .baseUrl("https://docinthefamily.matrixmarketers.com/")
            .client(okhttpclient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        handlerr= retrofit?.create(APICallMethodsKotlin::class.java)
    }
    fun getHandler() : APICallMethodsKotlin?{
        return  this.handlerr
    }
}