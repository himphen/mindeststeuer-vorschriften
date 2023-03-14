package com.example.appstore.domain.album

import com.example.appstore.db.album.AlbumDao
import com.example.appstore.db.album.BookmarkedAlbumEntity

class AddBookmarked(
    private val dao: AlbumDao
) {
    suspend operator fun invoke(collectionId: Long): Long {
        return dao.add(
            BookmarkedAlbumEntity(
                collectionId = collectionId
            )
        )
    }
}
