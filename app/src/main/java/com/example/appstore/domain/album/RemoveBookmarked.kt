package com.example.appstore.domain.album

import com.example.appstore.db.album.AlbumDao

class RemoveBookmarked(
    private val dao: AlbumDao
) {
    suspend operator fun invoke(collectionId: Long) {
        dao.remove(collectionId)
    }
}
