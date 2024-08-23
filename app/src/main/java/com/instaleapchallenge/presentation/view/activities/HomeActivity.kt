package com.instaleapchallenge.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.instaleapchallenge.R
import com.instaleapchallenge.databinding.ActivityHomeBinding
import com.instaleapchallenge.domain.model.Movie
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import com.instaleapchallenge.presentation.view.adapter.viewholder.MovieAdapter
import com.instaleapchallenge.presentation.view.viewmodels.MoviesSearchViewModel
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val moviesSearchViewModel: MoviesSearchViewModel by inject()
    private var moviesList = emptyList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        prepareLayout()
        listenLiveData()
        moviesSearchViewModel.getMovies()
    }

    private fun prepareLayout() {
        binding.tabLayout.apply {
            addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    // Handle tab select
                    val position = tab.position
                    when (position) {
                        0 -> {
                            setDataAdapter(moviesList)
                        }

                        1 -> {

                        }

                        2 -> {

                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            })

        }
    }

    private fun listenLiveData() {
        moviesSearchViewModel.moviesActionsLiveData.observe(this@HomeActivity) {
            if (it is MoviesActions.OnMoviesReceived) {
                binding.apply {
                    moviesList = it.movies ?: emptyList()
                    progressBar.isVisible = false
                    setDataAdapter(moviesList)
                }

            }
            if (it is MoviesActions.OnMoviesReceivedError) {
                binding.progressBar.isVisible = false
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setDataAdapter(moviesList: List<Movie>) {
        binding.apply {
            recyclerView.adapter = MovieAdapter(moviesList)
            recyclerView.layoutManager =
                LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
        }
    }
}