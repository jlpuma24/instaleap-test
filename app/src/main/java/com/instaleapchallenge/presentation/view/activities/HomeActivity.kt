package com.instaleapchallenge.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.instaleapchallenge.R
import com.instaleapchallenge.databinding.ActivityHomeBinding
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import com.instaleapchallenge.presentation.view.adapter.viewholder.MovieAdapter
import com.instaleapchallenge.presentation.view.viewmodels.MoviesSearchViewModel
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val moviesSearchViewModel: MoviesSearchViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        listenLiveData()
        moviesSearchViewModel.getMovies()
    }

    private fun listenLiveData() {
        moviesSearchViewModel.moviesActionsLiveData.observe(this@HomeActivity) {
            if (it is MoviesActions.OnMoviesReceived) {
                binding.apply {
                    progressBar.isVisible = false
                    recyclerView.adapter = MovieAdapter(it.movies ?: listOf())
                    recyclerView.layoutManager =
                        LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
                }

            }
            if (it is MoviesActions.OnMoviesReceivedError) {
                binding.progressBar.isVisible = false
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG).show()
            }
        }
    }
}