package com.instaleapchallenge.domain.usecases

import com.instaleapchallenge.domain.model.Movie
import com.instaleapchallenge.domain.repository.search.MoviesSearchRepository
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetMoviesUseCaseTest {

    private lateinit var useCase: GetMoviesUseCase
    private val repository = mockk<MoviesSearchRepository>()

    @Before
    fun setUp() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `execute returns OnMoviesReceived on success`() = runBlockingTest {
        val mockMovies = listOf(Movie(true, "url", listOf(), -1, "", "", "", 0.0, "", "", "", true, 0.0, 0))
        coEvery { repository.getMovies() } returns mockMovies

        val result = useCase.execute()
        assertTrue(result is MoviesActions.OnMoviesReceived)
        assertEquals(mockMovies, (result as MoviesActions.OnMoviesReceived).movies)
    }

    @Test
    fun `execute returns OnMoviesReceivedError on exception`() = runBlockingTest {
        coEvery { repository.getMovies() } throws IOException()

        val result = useCase.execute()
        assertTrue(result is MoviesActions.OnMoviesReceivedError)
    }
}