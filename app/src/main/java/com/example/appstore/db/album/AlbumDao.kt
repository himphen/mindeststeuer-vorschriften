package com.example.appstore.db.album

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface AlbumDao {
    @Transaction
    @Query("SELECT * FROM bookmarked_album")
    fun getAllAsLiveData(): LiveData<List<BookmarkedAlbumEntity>>

    @Insert
    suspend fun add(entity: BookmarkedAlbumEntity): Long

    @Query("DELETE FROM bookmarked_album WHERE collectionId = (:collectionId)")
    suspend fun remove(
        collectionId: Long,
    )

    @Query("DELETE FROM bookmarked_album")
    suspend fun clearAll()
}
