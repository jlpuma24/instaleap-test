package com.instaleapchallenge.framework.di

import com.instaleapchallenge.domain.repository.search.MoviesSearchRepository
import com.instaleapchallenge.domain.repository.search.MoviesSearchRepositoryImpl
import org.koin.dsl.module

val repositoryModules = module {
    single<MoviesSearchRepository> { MoviesSearchRepositoryImpl(get()) }
}