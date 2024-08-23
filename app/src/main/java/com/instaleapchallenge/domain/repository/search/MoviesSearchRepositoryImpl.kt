package com.instaleapchallenge.domain.repository.search

import com.instaleapchallenge.domain.model.Movie
import com.instaleapchallenge.domain.network.MoviesSearchApiService
import com.instaleapchallenge.domain.network.exceptions.ApiErrorException
import com.instaleapchallenge.domain.network.exceptions.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import java.io.IOException

class MoviesSearchRepositoryImpl(private val apiService: MoviesSearchApiService) :
    MoviesSearchRepository,
    KoinComponent {

    override suspend fun getMovies(): List<Movie>? =
        withContext(Dispatchers.IO) {
            val response = apiService.getMovies()
            try {
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    throw ApiErrorException()
                }
            } catch (e: IOException) {
                throw NoConnectivityException()
            }
        }
}