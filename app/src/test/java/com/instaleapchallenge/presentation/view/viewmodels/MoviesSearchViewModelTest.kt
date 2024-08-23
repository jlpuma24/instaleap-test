import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instaleapchallenge.domain.model.Movie
import com.instaleapchallenge.domain.usecases.GetMoviesUseCase
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import com.instaleapchallenge.presentation.view.viewmodels.MoviesSearchViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class MoviesSearchViewModelTest {

    private lateinit var viewModel: MoviesSearchViewModel
    private val getMoviesUseCase = mockk<GetMoviesUseCase>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MoviesSearchViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getMovies posts OnMoviesReceived action on success`() = runBlockingTest {
        val mockMovies = listOf(Movie(true, "url", listOf(), -1, "", "", "", 0.0, "", "", "", true, 0.0, 0))
        coEvery { getMoviesUseCase.execute() } returns MoviesActions.OnMoviesReceived(mockMovies)

        viewModel.getMovies()
        assertEquals(mockMovies, (viewModel.moviesActionsLiveData.value as? MoviesActions.OnMoviesReceived)?.movies)
    }

    @Test
    fun `getMovies posts OnMoviesReceivedError action on failure`() = runBlockingTest {
        coEvery { getMoviesUseCase.execute() } returns MoviesActions.OnMoviesReceivedError

        viewModel.getMovies()
        assertTrue(viewModel.moviesActionsLiveData.value is MoviesActions.OnMoviesReceivedError)
    }
}
