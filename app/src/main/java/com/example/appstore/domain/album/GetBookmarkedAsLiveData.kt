package com.example.appstore.domain.album

import androidx.lifecycle.LiveData
import com.example.appstore.db.album.BookmarkedAlbumEntity
import com.example.appstore.repository.BookmarkRepository

class GetBookmarkedAsLiveData(
    private val repository: BookmarkRepository
) {
    operator fun invoke(): LiveData<List<BookmarkedAlbumEntity>> {
        return repository.getAllAsLiveData()
    }
}
