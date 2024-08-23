package com.instaleapchallenge.domain.network

import com.instaleapchallenge.BuildConfig
import com.instaleapchallenge.domain.model.Movie
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val MOVIES_PATH = "5c9e8118-5a39-4005-8eb6-cdff059b8c25"

interface MoviesSearchApiService {
    @GET(MOVIES_PATH)
    suspend fun getMovies(): Response<List<Movie>>

    companion object : KoinComponent {
        fun create(
            converterFactory: GsonConverterFactory,
            client: OkHttpClient
        ): MoviesSearchApiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build().create(MoviesSearchApiService::class.java)
    }
}