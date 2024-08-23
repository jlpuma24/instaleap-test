package com.instaleapchallenge.framework.di

import com.instaleapchallenge.domain.network.MoviesSearchApiService
import com.instaleapchallenge.domain.network.interceptor.ConnectivityInterceptor
import com.instaleapchallenge.framework.di.util.Utils
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

private const val HTTP_SIMPLE_CLIENT = "HttpSimpleClient"
private const val CONNECTIVITY = "Connectivity"

val networkModule = module {
    single<GsonConverterFactory> { GsonConverterFactory.create() }
    single(named(CONNECTIVITY)) { ConnectivityInterceptor(androidContext()) }
    factory(named(HTTP_SIMPLE_CLIENT)) { Utils.getOkHttpClient(get(named(CONNECTIVITY))) }
    single { MoviesSearchApiService.create(get(), get(named(HTTP_SIMPLE_CLIENT))) }
}