package com.instaleapchallenge.presentation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instaleapchallenge.domain.usecases.GetMoviesUseCase
import com.instaleapchallenge.presentation.view.actions.MoviesActions
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MoviesSearchViewModel(private val moviesSearchUseCase: GetMoviesUseCase) : ViewModel(),
    KoinComponent {

    private val _moviesActionsLiveData: MutableLiveData<MoviesActions> = MutableLiveData(null)
    val moviesActionsLiveData: LiveData<MoviesActions>
        get() = _moviesActionsLiveData

    fun getMovies() = viewModelScope.launch {
        _moviesActionsLiveData.postValue(moviesSearchUseCase.execute())
    }
}