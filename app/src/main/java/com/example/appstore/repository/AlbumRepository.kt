package com.example.appstore.repository

import com.example.api.response.eta.AlbumsResponse
import com.example.appstore.api.ApiManager

class AlbumRepository(
    private val apiManager: ApiManager
) {

    suspend fun getAlbumFromApi(term: String): AlbumsResponse {
        return apiManager.iTunesService.getAlbums(term)
    }
}
