package com.instaleapchallenge.presentation.view.actions

import com.instaleapchallenge.domain.model.Movie

sealed class MoviesActions {
    class OnMoviesReceived(val movies: List<Movie>?): MoviesActions()
    object OnMoviesReceivedError: MoviesActions()
}