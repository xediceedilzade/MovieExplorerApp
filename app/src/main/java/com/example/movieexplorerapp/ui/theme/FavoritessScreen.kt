package com.example.movieexplorerapp.ui.theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorerapp.MovieRepository
import com.example.movieexplorerapp.viewmodel.MovieViewModel

@Composable
fun FavoritessScreen(
viewModel: MovieViewModel,
onMovieClick:(Int)-> Unit
) {
    val favoriteMovieIds by viewModel.favoriteMovieIds.collectAsState()
    val favoriteMovies= MovieRepository.movies.filter { movie->
        movie.id in favoriteMovieIds
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorites"
        )
        if (favoriteMovies.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "❤\uFE0F"
                )
                Text(
                    text = "No favorites movies yet"
                )
            }
        }else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier=Modifier
                    .fillMaxWidth()
                    .padding(top=16.dp),
                contentPadding = PaddingValues(bottom=16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteMovies){movie->
                    Column {
                        MovieCard(
                            movie=movie,
                            onClick = {
                                onMovieClick(movie.id)
                            }
                        )
                        Button(
                            onClick = {
                                viewModel.toggleFavorite(movie.id)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=8.dp)
                        ) {
                            Text(
                                text = "Remove from Favorites"
                            )
                        }
                    }

                }
            }
        }
    }
}