package com.example.api.login

import com.example.api.RetrofitManager
import com.example.api.login.request.LoginRequest
import com.example.api.login.responses.LoginResponse
import com.example.api.login.responses.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface ApiLogin {

    //Obtiene token de auth de la Api
    @GET("authentication/token/new")
    fun getAuthToken(
        @Query("api_key") apiKey:String = RetrofitManager.API_KEY
    ):Call<TokenResponse>?

    @POST("authentication/token/validate_with_login")
    fun authenticate(
        @Query("api_key") apiKey:String = RetrofitManager.API_KEY,
        @Body request:LoginRequest
    ):Call<LoginResponse>?

}