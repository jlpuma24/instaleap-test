package com.instaleapchallenge.presentation.view.adapter.viewholder

import com.instaleapchallenge.domain.model.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instaleapchallenge.databinding.RowMovieBinding

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            RowMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.setData(movie.posterPath ?: movie.backdropPath ?: "", movie.title, movie.overview)
    }

    override fun getItemCount() = movies.size
}
