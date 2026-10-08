package com.example.movieexplorerapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieexplorerapp.R
import com.example.movieexplorerapp.ui.theme.FavoritessScreen
import com.example.movieexplorerapp.ui.theme.HomeScreen
import com.example.movieexplorerapp.ui.theme.MovieDetailScreen
import com.example.movieexplorerapp.ui.theme.ProfileScreen
import com.example.movieexplorerapp.viewmodel.MovieViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    // Bütün ekranlar üçün EYNİ ViewModel
    val movieViewModel: MovieViewModel = viewModel()

    Scaffold(
        bottomBar = {

            NavigationBar {

                val navBackStackEntry by navController
                    .currentBackStackEntryAsState()

                val currentRoute =
                    navBackStackEntry?.destination?.route

                // HOME
                NavigationBarItem(
                    selected = currentRoute == "home",
                    onClick = {
                        navController.navigate("home")
                    },
                    icon = {
                        Image(
                            painter = painterResource(
                                R.drawable.ic_home
                            ),
                            contentDescription = "Home"
                        )
                    }
                )

                // FAVORITES
                NavigationBarItem(
                    selected = currentRoute == "favorites",
                    onClick = {
                        navController.navigate("favorites")
                    },
                    icon = {
                        Image(
                            painter = painterResource(
                                R.drawable.ic_favorites
                            ),
                            contentDescription = "Favorites"
                        )
                    }
                )

                // PROFILE
                NavigationBarItem(
                    selected = currentRoute == "profile",
                    onClick = {
                        navController.navigate("profile")
                    },
                    icon = {
                        Image(
                            painter = painterResource(
                                R.drawable.ic_profile
                            ),
                            contentDescription = "Profile"
                        )
                    }
                )
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {

            // HOME
            composable("home") {

                HomeScreen(
                    viewModel = movieViewModel,
                    onMovieClick = { movieId ->

                        navController.navigate(
                            "movie_detail/$movieId"
                        )
                    }
                )
            }

            // FAVORITES
            composable("favorites") {

                FavoritessScreen(
                    viewModel = movieViewModel,
                    onMovieClick = { movieId ->

                        navController.navigate(
                            "movie_detail/$movieId"
                        )
                    }
                )
            }

            // PROFILE
            composable("profile") {

                ProfileScreen(
                    viewModel=movieViewModel
                )
            }

            // MOVIE DETAIL
            composable(
                route = "movie_detail/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val movieId =
                    backStackEntry.arguments
                        ?.getInt("movieId") ?: 0

                MovieDetailScreen(
                    movieId = movieId,

                    // ÇOX VACİB:
                    // Detail də eyni ViewModel istifadə edir
                    viewModel = movieViewModel,

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}