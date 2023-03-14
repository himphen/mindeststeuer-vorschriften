package com.example.api.service

import com.example.api.response.eta.AlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {
    @GET("search?entity=album")
    suspend fun getAlbums(
        @Query("term") term: String
    ): AlbumsResponse
}
