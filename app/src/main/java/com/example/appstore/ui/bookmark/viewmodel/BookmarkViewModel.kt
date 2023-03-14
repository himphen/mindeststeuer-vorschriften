package com.example.appstore.ui.bookmark.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appstore.domain.album.AlbumInteractor
import com.example.appstore.model.Album
import com.example.appstore.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val albumInteractor: AlbumInteractor,
) : BaseViewModel() {

    val bookmarkedRemoved = MutableSharedFlow<Boolean>()

    private val albumListLiveData = MutableLiveData<List<Album>>()
    private val bookmarkedListLiveData = albumInteractor.getBookmarkedAsLiveData()

    var bookmarkedAlbumListLiveData = MediatorLiveData<List<Album>>()

    init {
        bookmarkedAlbumListLiveData.addSource(albumListLiveData) {
            combineList()
        }

        bookmarkedAlbumListLiveData.addSource(bookmarkedListLiveData) {
            combineList()
        }
    }

    private fun combineList() {
        val bookmarkedList = bookmarkedListLiveData.value ?: emptyList()
        val bookmarkedIdList = bookmarkedList.map {
            it.collectionId
        }

        val newData = albumListLiveData.value?.mapNotNull {
            val new = it.copy()
            new.isBookmarked = bookmarkedIdList.contains(new.collectionId)

            if (new.isBookmarked) {
                return@mapNotNull new
            } else {
                return@mapNotNull null
            }
        } ?: emptyList()


        bookmarkedAlbumListLiveData.postValue(newData)
    }

    fun getListFromAPI() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, t -> run {} }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val data = albumInteractor.getAlbumsFromAPI.invoke("Jack Johnson")

            albumListLiveData.postValue(data?.map {
                Album.convertFrom(it)
            } ?: emptyList())
        }
    }

    fun removeBookmarked(collectionId: Long) {
        viewModelScope.launch {
            albumInteractor.removeBookmarked(collectionId)
            bookmarkedRemoved.emit(true)
        }
    }
}
