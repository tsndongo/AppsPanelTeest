package com.tsndongo.appspaneltest.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    final val apKey = "uD4Muli8nO6nzkSlsNM3d1Pm"


    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("X-AP-Key", apKey)
                .header("X-AP-DeviceUID", "trial")
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test-pgt-dev.apnl.ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}