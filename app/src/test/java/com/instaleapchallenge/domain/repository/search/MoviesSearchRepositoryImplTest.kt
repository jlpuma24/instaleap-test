package com.instaleapchallenge.domain.repository.search

import com.instaleapchallenge.domain.model.Movie
import com.instaleapchallenge.domain.network.MoviesSearchApiService
import com.instaleapchallenge.domain.network.exceptions.ApiErrorException
import org.junit.Assert.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import org.junit.Before
import org.junit.Test

class MoviesSearchRepositoryImplTest {

    private lateinit var repository: MoviesSearchRepositoryImpl
    private val apiService = mockk<MoviesSearchApiService>(relaxed = true)

    @Before
    fun setUp() {
        repository = MoviesSearchRepositoryImpl(apiService)
    }

    @Test
    fun `getMovies success returns list of movies`() = runBlocking {
        val mockMovies = listOf(Movie(true, "url", listOf(), -1, "", "", "", 0.0, "", "", "", true, 0.0, 0))
        coEvery { apiService.getMovies() } returns Response.success(mockMovies)

        val result = repository.getMovies()
        assertEquals(mockMovies, result)
    }

    @Test
    fun `getMovies failure throws ApiErrorException`() = runBlocking {
        val responseBody = "".toResponseBody(null)
        coEvery { apiService.getMovies() } returns Response.error(400, responseBody)

        try {
            repository.getMovies()
            fail("ApiErrorException was expected but not thrown")
        } catch (e: ApiErrorException) {
            // Expected exception
        } catch (e: Exception) {
            fail("Expected ApiErrorException, but got ${e::class.java.simpleName}")
        }
    }
}
