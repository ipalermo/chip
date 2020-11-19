package com.chip.challenge.api

import com.chip.challenge.data.AllBreedsResponse
import com.chip.challenge.data.RandomImagesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Used to connect to the Dog API to fetch breeds and dog pics
 */
interface DogService {

    @GET("api/breeds/list/all")
    suspend fun allBreeds(): AllBreedsResponse

    @GET("api/breed/{breedName}/images/random/{quantity}")
    suspend fun breedRandomImage(
        @Path("breedName") breedName: String,
        @Path("quantity") quantity: Int
    ): RandomImagesResponse

    companion object {
        private const val BASE_URL = "https://dog.ceo/"

        fun create(): DogService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogService::class.java)
        }
    }
}
