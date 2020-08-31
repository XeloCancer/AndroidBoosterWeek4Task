package com.useless.boosterapp4.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit{
        if(retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl("INSERT BASE URL HERE FROM API DOCUMENTATION/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()

        return retrofit!!
    }

}
