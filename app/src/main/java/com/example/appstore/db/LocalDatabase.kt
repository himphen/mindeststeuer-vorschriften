package com.example.appstore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appstore.db.album.AlbumDao
import com.example.appstore.db.album.BookmarkedAlbumEntity

private const val DATABASE_NAME = "saved_data"

@Database(
    entities = [
        BookmarkedAlbumEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

        fun getInstance(context: Context): LocalDatabase {
            val dbBuilder = Room.databaseBuilder(
                context,
                LocalDatabase::class.java,
                DATABASE_NAME
            )
            dbBuilder.fallbackToDestructiveMigration()
//            dbBuilder.setQueryCallback({ sqlQuery, bindArgs ->
//                Log.d("SQL", "Query: $sqlQuery SQL --- Args: $bindArgs")
//            }, Executors.newSingleThreadExecutor())
            return dbBuilder.build()
        }
    }
}