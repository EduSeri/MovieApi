package com.example.views.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.home.HomeWebService
import com.example.api.home.response.MovieResponse
import com.example.api.login.LoginWebService
import com.example.views.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val homeWebService = HomeWebService()

    val moviesLiveData = MutableLiveData<List<MovieResponse>>()

    private val threshold = 10
    private val pageSize = 20
    private var nextPage = 1
    private var lastMovie = 0
    private var lastVisibleElement = 0

    private val movieList = mutableListOf<MovieResponse>()

    fun getMovies(page : Int = nextPage){
        viewModelScope.launch(Dispatchers.IO) {
            val call = homeWebService.getMovieList(page = page)?.execute()
            val body = call?.body()

            if (call?.isSuccessful == true) {
                body?.movies?.forEach{ movie ->
                    if(!movieList.contains(movie)){
                        movieList.add(movie)
                    }
                }
                moviesLiveData.postValue(movieList)
            } else {
                moviesLiveData.postValue( emptyList())
            }
        }
    }


    fun notifyLastElement (lastElement:Int){
        if(lastVisibleElement != lastElement){
            lastVisibleElement=lastElement
            checkNewPage()
        }
    }


    private fun checkNewPage(){
        if(lastVisibleElement+threshold>=lastMovie){
            lastMovie += pageSize
            nextPage ++
            getMovies()
        }
    }



}