package com.instaleapchallenge.domain.repository.search

import com.instaleapchallenge.domain.model.Movie

interface MoviesSearchRepository {
    suspend fun getMovies(): List<Movie>?
}