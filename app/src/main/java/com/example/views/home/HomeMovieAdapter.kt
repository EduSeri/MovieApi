package com.example.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.home.response.MovieListResponse
import com.example.api.home.response.MovieResponse
import com.example.movieapi.databinding.MovieItemBinding

class HomeMovieAdapter(
    private val listener: MovieListener
) : ListAdapter<MovieResponse, HomeMovieAdapter.ViewHolder>(HomeMoviesDiffUtilsCallBack()), Filterable {

    private val movieList = mutableListOf<MovieResponse>()
    private var filteredList = listOf<MovieResponse>()

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(title: CharSequence?): FilterResults {
               filteredList= movieList.filter { movie ->
                   movie.title.contains(title.toString(), ignoreCase = true)
               }
                return FilterResults().apply {
                    values=filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults) {
                filteredList=if(result.values==null){
                    emptyList()
                }else{
                    @Suppress("UNCHECKED_CAST")
                    result.values as List<MovieResponse>
                }
                submitList(filteredList)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            titleText.text = getItem(position).title
            image.loadImage(getItem(position).posterPath)
            overViewText.text= getItem(position).overview
            ratingText.text= getItem(position).voteAverage.toString()
            dateText.text=getItem(position).releaseDate
            movieLayout.setOnClickListener{
                listener.onClick(getItem(position).title)
            }
        }
    }

    fun onNewData (list : List<MovieResponse>){
        list.forEach{ movie ->
            if(!movieList.contains(movie)){
                movieList.add(movie)
            }
        }
        filteredList=movieList
        submitList(filteredList)
    }

    class ViewHolder (val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

}