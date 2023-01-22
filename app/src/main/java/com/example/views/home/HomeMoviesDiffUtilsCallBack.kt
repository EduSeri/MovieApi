package com.example.views.home

import androidx.recyclerview.widget.DiffUtil
import com.example.api.home.response.MovieResponse

class HomeMoviesDiffUtilsCallBack: DiffUtil.ItemCallback<MovieResponse>() {
    override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
        return oldItem==newItem
    }


}