package com.example.api.home

import com.example.api.RetrofitManager
import com.example.api.home.response.MovieListResponse
import com.example.api.login.ApiLogin
import retrofit2.Call

class HomeWebService : ApiHome {
    override fun getMovieList(apiKey: String, page: Int): Call<MovieListResponse>? {
        return RetrofitManager.getInstance()?.create(ApiHome::class.java)?.getMovieList(page=page)
    }

}