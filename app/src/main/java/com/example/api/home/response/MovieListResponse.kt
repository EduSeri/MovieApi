package com.example.api.home.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

    val page : Int,
    @SerializedName("results") val movies : List<MovieResponse>,
    @SerializedName("total_results") val totalResult: Int,
    @SerializedName("total_page") val totalPage: Int

)

data class MovieResponse(

    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double

)
