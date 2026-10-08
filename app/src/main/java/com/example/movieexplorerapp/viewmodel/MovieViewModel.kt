package com.example.movieexplorerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorerapp.Movie
import com.example.movieexplorerapp.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel (
    application: Application
) : AndroidViewModel(application){
    //LOADING
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> =_isLoading.asStateFlow()
    init{
        viewModelScope.launch {
            delay(800)
            _isLoading.value=false
        }
    }
    //FAVORITES
    private val preferences =
        application.getSharedPreferences(
            "movie_preferences",
            Application.MODE_PRIVATE
        )
    private val savedFavorites=
        preferences.getStringSet(
            "favorite_movies",
            emptySet()
        )?:emptySet()
    private val _favoriteMovieIds=
        MutableStateFlow(savedFavorites.map { it.toInt() }.toSet()
        )
    val favoriteMovieIds: StateFlow<Set<Int>> =
        _favoriteMovieIds.asStateFlow()
    private val allMovies = MovieRepository.movies

    private val _movies = MutableStateFlow(allMovies)
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private val _selectedGenre = MutableStateFlow("All")

    val selectedGenre: StateFlow<String> =
            _selectedGenre.asStateFlow()

    //Search+Genre filter
    private fun filterMovies(){
        val query =_searchQuery.value
        val genre =_selectedGenre.value
        _movies.value =allMovies.filter{movie ->
            val matchesSearch =
                movie.title.contains(
                    query,
                    ignoreCase = true
                )
            val matchesGenre =
                genre =="All"||
                        movie.genre ==genre
            matchesSearch &&matchesGenre
        }
    }

//Search
    fun searchMovies(query: String){
        _searchQuery.value=query
       filterMovies()
    }
    fun selectedGenre(genre: String){
        _selectedGenre.value =genre
        filterMovies()
    }

    fun toggleFavorite(movieId: Int){
        val currentFavorites=_favoriteMovieIds.value.toMutableSet()
        if (movieId in currentFavorites){
            currentFavorites.remove(movieId)
        }    else{
            currentFavorites.add(movieId)
        }
        _favoriteMovieIds.value=currentFavorites
        //yadda saxla
        preferences.edit()
            .putStringSet(
                "favorite_movies",
                currentFavorites
                    .map { it.toString() }
                    .toSet()
            )
            .apply()
    }
    fun isFavorite(movieId: Int): Boolean{
        return movieId in _favoriteMovieIds.value
    }
}