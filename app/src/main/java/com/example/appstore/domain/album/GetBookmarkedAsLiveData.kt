package com.example.appstore.domain.album

import androidx.lifecycle.LiveData
import com.example.appstore.db.album.AlbumDao
import com.example.appstore.db.album.BookmarkedAlbumEntity

class GetBookmarkedAsLiveData(
    private val dao: AlbumDao
) {
    operator fun invoke(): LiveData<List<BookmarkedAlbumEntity>> {
        return dao.getAllAsLiveData()
    }
}
