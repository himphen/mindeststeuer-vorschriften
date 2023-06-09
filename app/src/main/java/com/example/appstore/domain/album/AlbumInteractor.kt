package com.example.appstore.domain.album

class AlbumInteractor(
    val getAlbumsFromAPI: GetAlbumsFromAPI,
    val getBookmarkedAsLiveData: GetBookmarkedAsLiveData,
    val addBookmarked: AddBookmarked,
    val removeBookmarked: RemoveBookmarked,
)
