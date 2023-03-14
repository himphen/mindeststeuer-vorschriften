package com.example.appstore.domain.album

import com.example.api.model.Album
import com.example.appstore.api.ApiSafeCall
import com.example.appstore.api.Resource
import com.example.appstore.repository.AlbumRepository

class GetAlbumsFromAPI(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(term: String): List<Album>? {
        val result = ApiSafeCall { repository.getAlbumFromApi(term) }

        val data = when (result) {
            is Resource.Success -> result.getData()
            is Resource.HttpError -> throw result.getThrowable()
            is Resource.OtherError -> throw result.getThrowable()
        }

        return data.results
    }
}
