package com.example.api

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {

    companion object {

        const val API_KEY= "8364420a4c571b12298a688b23e8665d"
        private var manager: Retrofit?=null

        //Obtener instancia de la clase para no repetir instancias
        fun getInstance():Retrofit?{
            return manager
        }
    }

    fun initiateRetrofit(context: Context){
        //Debug de peticiones web
        val okHttpClient= OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()

        //Cliente de retrofit, hace peticiones
        manager = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

