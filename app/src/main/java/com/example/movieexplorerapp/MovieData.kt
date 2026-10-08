package com.example.movieexplorerapp
import com.example.movieexplorerapp.R

object MovieRepository {

    val movies = listOf(
        Movie(
            id = 1,
            title = "Inception",
            rating = 8.8,
            year = 2010,
            genre = "Sci-Fi",
            duration = "2h 28m",
            description = "A thief who steals corporate secrets through dream-sharing technology.",
            image = R.drawable.inception
        ),

        Movie(
            id = 2,
            title = "Avatar",
            rating = 8.1,
            year = 2009,
            genre = "Sci-Fi",
            duration = "2h 42m",
            description = "A marine becomes part of a new world on another planet.",
            image = R.drawable.avatar_800x1200_208c9665
        ),

        Movie(
            id = 3,
            title = "Batman",
            rating = 8.2,
            year = 2022,
            genre = "Action",
            duration = "2h 56m",
            description = "Batman investigates a series of crimes in Gotham City.",
            image = R.drawable.batman
        ),

        Movie(
            id = 4,
            title = "Interstellar",
            rating = 8.7,
            year = 2014,
            genre = "Sci-Fi",
            duration = "2h 49m",
            description = "A team travels through space searching for a new home for humanity.",
            image = R.drawable.interstellar
        ),
        Movie(
            id=5,
            title="The Dark Knight",
            rating=9.0,
            year=2008,
            genre="Action",
            duration="2h 32m",
            description = "Batman faces a dangerous criminal mastermind known as the Joker.",
            image = R.drawable.the_dark_knight
        ),
        Movie(
            id=6,
            title="Avengers",
            rating=8.0,
            year=2012,
            genre="Action",
            duration = "2h 23m",
            description = "Earth's greatest heroes come together to fight a powerful enemy.",
            image = R.drawable.teh_avengers
        ),
        Movie(
            id=7,
            title="The Hangover",
            rating=7.7,
            year = 2009,
            genre = "Comedy",
            duration = "1h 40m",
            description = "Three friends try to remember what happened during a wild night in Las Vegas.",
            image = R.drawable.hangover
        ),
        Movie(
            id=8,
            title = "Superbad",
            rating=7.6,
            year=2007,
            genre="Comedy",
            duration = "1h 53m",
            description = "Two high school friends try to enjoy one unforgettable night before graduation.",
            image = R.drawable.superbad
        ),
        Movie(
            id=9,
            title = "The Mask",
            rating = 7.0,
            year = 1994,
            genre = "Comedy",
            duration = "1h 41m",
            description = "A shy man discovers a magical mask that completely changes his life.",
            image =R.drawable.the_mask
        ),
        Movie(
            id = 10,
            title = "Joker",
            rating = 8.4,
            year = 2019,
            genre = "Action",
            duration = "2h 2m",
            description = "A troubled man slowly transforms into a mysterious criminal figure.",
            image = R.drawable.joker
        ),

        Movie(
            id = 11,
            title = "Titanic",
            rating = 7.9,
            year = 1997,
            genre = "Romance",
            duration = "3h 14m",
            description = "A young couple falls in love aboard the legendary Titanic.",
            image = R.drawable.titanic
        ),

        Movie(
            id = 12,
            title = "The Matrix",
            rating = 8.7,
            year = 1999,
            genre = "Sci-Fi",
            duration = "2h 16m",
            description = "A computer programmer discovers that reality is not what it seems.",
            image = R.drawable.matrix
        )
    )
}