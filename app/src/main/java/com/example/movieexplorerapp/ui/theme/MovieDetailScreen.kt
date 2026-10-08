package com.example.movieexplorerapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorerapp.MovieRepository
import com.example.movieexplorerapp.Movie
import androidx.compose.foundation.Image
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.movieexplorerapp.viewmodel.MovieViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
) {
    val context=LocalContext.current

    val movie = MovieRepository.movies.find {
        it.id == movieId
    }
    val favoriteMovieIds by viewModel.favoriteMovieIds.collectAsState()
    val isFavorite =movieId in favoriteMovieIds

    if (movie == null) {
        Text("Movie not found")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = onBackClick
        ) {
            Text("← Back")
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
        if (movie.image!=0){
            Image(
                painter = painterResource(id=movie.image),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }

        Text(
            text = movie.title
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "⭐ ${movie.rating}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "${movie.year} • ${movie.genre} • ${movie.duration}"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = movie.description
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                viewModel.toggleFavorite(movieId)
            },
            modifier=Modifier.fillMaxWidth()

        ) {
            Text(
                text = if (isFavorite){
                    "❤\uFE0F Remove from Favorites"
                }else{
                    "♡ Add to Favorites"
                }
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Trailer coming soon",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Watch Trailer")
        }
    }
}