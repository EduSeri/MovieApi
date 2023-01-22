package com.example.api.home

import com.example.api.RetrofitManager
import com.example.api.home.response.MovieListResponse
import com.example.api.login.responses.TokenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHome {

    @GET("movie/popular")
    fun getMovieList(
        @Query("api_key") apiKey:String = RetrofitManager.API_KEY,
        @Query("page") page:Int
    ): Call<MovieListResponse>?

}