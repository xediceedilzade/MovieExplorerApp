package com.example.movieexplorerapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieexplorerapp.Movie
import com.example.movieexplorerapp.viewmodel.MovieViewModel
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.ui.res.painterResource
import com.example.movieexplorerapp.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    onMovieClick: (Int) -> Unit
) {

    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Movies",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                viewModel.searchMovies(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            placeholder = {
                Text("Search movies...")
            },
            singleLine = true
        )
        Column {

            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                val genres = listOf(
                    "All",
                    "Action",
                    "Comedy",
                    "Sci-Fi"
                )

                genres.forEach { genre ->

                    FilterChip(
                        selected = selectedGenre == genre,
                        onClick = {
                            viewModel.selectedGenre(genre)
                        },
                        label = {
                            Text(genre)
                        }
                    )
                }
            }
        }
        if (isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
               CircularProgressIndicator()
            }
        }else if (movies.isEmpty()){
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top=60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter=painterResource(R.drawable.movie_svgrepo_com),
                    contentDescription = "No movies",
                    modifier=Modifier.height(60.dp)
                )
                Text(
                    text = "No movies avaliable",
                    style = MaterialTheme.typography.titleMedium,
                    modifier= Modifier.padding(top=12.dp)
                )
            }
        }else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(movies) { movie ->

                    MovieCard(
                        movie = movie,
                        onClick = {
                            onMovieClick(movie.id)
                        }
                    )
                }
            }
        }
        }



    }



@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp)
    ) {

        Column {

            if (movie.image != 0) {

                Image(
                    painter = painterResource(id = movie.image),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

            } else {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "IMAGE",
                        color = Color.DarkGray
                    )
                }
            }

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "⭐ ${movie.rating}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}