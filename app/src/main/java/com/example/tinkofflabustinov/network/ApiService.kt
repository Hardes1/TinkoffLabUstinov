package com.example.tinkofflabustinov.network

import com.example.tinkofflabustinov.data.GifPicture
import com.example.tinkofflabustinov.data.GifPicturesList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://developerslife.ru/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
    MoshiConverterFactory.create(
        moshi
    )
).build()

interface GifApiService {
    @GET("random")
    fun getRandomGif(@Query("json") isJson: String = "true"): Call<GifPicture>

    @GET("{category}/{page}")
    fun getListOfGifs(
        @Path("category") category: String, @Path("page") page: String,
        @Query("json") isJson: String = "true"
    ): Call<GifPicturesList>
}


object GifApi {
    val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }
}