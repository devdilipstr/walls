package com.example.walls

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://api.unsplash.com/"
const val CLIENT_KEY = "wlyjl3oUQ2Pc3xEt3pBQqMlGkwvQQ5XSaKqSU_vbdUk"

interface Retrofetch {
    @GET("photos?client_id=$CLIENT_KEY")
    fun getBestWalls(): Call<MutableList<Image>>

    @GET("search/photos?client_id=$CLIENT_KEY")
    fun getWallByQuery(@Query("query") query: String,@Query("page") page:Int): Call<QueryResult>

    @GET("collections?client_id=$CLIENT_KEY")
    fun getCollection(): Call<MutableList<Category>>
}

object RetroFetchObject {
    val retrofetch: Retrofetch

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofetch = retrofit.create(Retrofetch::class.java)
    }
}
