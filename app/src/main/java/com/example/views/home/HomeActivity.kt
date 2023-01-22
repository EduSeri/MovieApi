package com.example.views.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityHomeBinding
import com.example.movieapi.databinding.ActivityLoginBinding
import com.example.views.login.LoginActivity
import com.example.views.login.LoginViewModel

class HomeActivity: AppCompatActivity() {

    private val viewModel = HomeViewModel()
    private lateinit var binding: ActivityHomeBinding

    private val movieListener = object : MovieListener{
        override fun onClick(title: String) {
            Toast.makeText(this@HomeActivity,title, Toast.LENGTH_SHORT).show()
        }

    }

    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return searchMovie(query)

        }

        override fun onQueryTextChange(newText: String): Boolean {
            return searchMovie(newText)
        }

        fun searchMovie(text: String):Boolean{
            val adapter = binding.movieList.adapter as HomeMovieAdapter
            adapter.filter.filter(text)
            return false
        }

    }

    private val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            /*
            SE QUEDA PILLADO, NO CONSIGUE COMPROBAR SI ESTAS PARADO O NO AL DESLIZAR

            if(newState==RecyclerView.SCROLL_STATE_IDLE){
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                viewModel.notifyLastElement(layoutManager.findLastCompletelyVisibleItemPosition())
            }
            */
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            viewModel.notifyLastElement(layoutManager.findLastCompletelyVisibleItemPosition())

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(LoginActivity.USERNAME)

        if(!username.isNullOrEmpty()) {
            binding.welcome.text = "Bienvenido $username"
            binding.welcome.visibility = View.VISIBLE
        }

        viewModel.getMovies()

        viewModel.moviesLiveData.observeForever { movies->
            if(!movies.isNullOrEmpty()){
                val adapter = binding.movieList.adapter as HomeMovieAdapter
                adapter.onNewData(movies)
            }
        }

        binding.movieList.adapter = HomeMovieAdapter(movieListener)

        binding.movieList.addOnScrollListener(scrollListener)

        binding.searchBar.setOnQueryTextListener(searchListener)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back)
    }


}