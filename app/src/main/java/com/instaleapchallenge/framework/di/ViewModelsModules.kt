package com.instaleapchallenge.framework.di

import com.instaleapchallenge.domain.usecases.GetMoviesUseCase
import com.instaleapchallenge.presentation.view.viewmodels.MoviesSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        MoviesSearchViewModel(
            GetMoviesUseCase(
                get()
            )
        )
    }
}