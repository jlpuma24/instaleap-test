package com.instaleapchallenge.domain.usecases

import com.instaleapchallenge.domain.repository.search.MoviesSearchRepository
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import org.koin.core.component.KoinComponent
import java.lang.Exception

class GetMoviesUseCase(
    private val moviesSearchRepository: MoviesSearchRepository
): KoinComponent {
    suspend fun execute(): MoviesActions {
        return try {
            val movies = moviesSearchRepository.getMovies()
            MoviesActions.OnMoviesReceived(movies)
        } catch (e: Exception) {
            MoviesActions.OnMoviesReceivedError
        }
    }
}