package com.theworld.androidtemplatewithnavcomponents.network

import com.theworld.androidtemplatewithnavcomponents.data.remote.Trending
import retrofit2.http.GET

interface Api {

    @GET("repo")
    suspend fun fetchTrendingRepos(): Trending

}
