package com.example.appstore.repository

import androidx.lifecycle.LiveData
import com.example.api.response.eta.AlbumsResponse
import com.example.appstore.api.ApiManager
import com.example.appstore.db.album.AlbumDao
import com.example.appstore.db.album.BookmarkedAlbumEntity

class BookmarkRepository(
    private val dao: AlbumDao
) {

    fun getAllAsLiveData(): LiveData<List<BookmarkedAlbumEntity>> {
        return dao.getAllAsLiveData()
    }
}
