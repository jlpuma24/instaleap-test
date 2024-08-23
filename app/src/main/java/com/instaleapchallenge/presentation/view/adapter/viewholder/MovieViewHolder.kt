package com.instaleapchallenge.presentation.view.adapter.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.instaleapchallenge.databinding.RowMovieBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(imageUrl: String, title: String, description: String) {
        binding.apply {
            Picasso.get()
                .load(imageUrl)
                .into(ivMovie, object: Callback {
                    override fun onSuccess() {
                        pbImageLoader.isVisible = false
                    }

                    override fun onError(e: Exception?) {
                        pbImageLoader.isVisible = false
                    }
                })
            tvMovieName.text = title
            tvDescription.text = description
        }
    }
}