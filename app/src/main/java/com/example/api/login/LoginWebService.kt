package com.example.api.login

import android.util.Log
import com.example.api.RetrofitManager
import com.example.api.login.request.LoginRequest
import com.example.api.login.responses.LoginResponse
import com.example.api.login.responses.TokenResponse
import retrofit2.Call
import retrofit2.Retrofit

class LoginWebService: ApiLogin {


    //Pedir instancia y crear la llamada
    override fun getAuthToken(apiKey: String): Call<TokenResponse>? {
        return RetrofitManager.getInstance()?.create(ApiLogin::class.java)?.getAuthToken()

    }

    override fun authenticate(apiKey: String, request: LoginRequest): Call<LoginResponse>? {
        return RetrofitManager.getInstance()?.create(ApiLogin::class.java)?.authenticate(request = request)
    }
}