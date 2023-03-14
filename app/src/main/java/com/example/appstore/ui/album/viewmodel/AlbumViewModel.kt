package com.example.appstore.ui.album.viewmodel

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

class AlbumViewModel(
    private val albumInteractor: AlbumInteractor,
) : BaseViewModel() {

    val bookmarkedAdded = MutableSharedFlow<Boolean>()
    val bookmarkedRemoved = MutableSharedFlow<Boolean>()

    private val albumListLiveData = MutableLiveData<List<Album>>()
    private val bookmarkedListLiveData = albumInteractor.getBookmarkedAsLiveData()

    var albumListWithBookmarkedData = MediatorLiveData<List<Album>>()

    init {
        albumListWithBookmarkedData.addSource(albumListLiveData) {
            combineList()
        }

        albumListWithBookmarkedData.addSource(bookmarkedListLiveData) {
            combineList()
        }
    }

    private fun combineList() {
        val bookmarkedList = bookmarkedListLiveData.value ?: emptyList()
        val bookmarkedIdList = bookmarkedList.map {
            it.collectionId
        }

        val newData = albumListLiveData.value?.map {
            val new = it.copy()
            new.isBookmarked = bookmarkedIdList.contains(new.collectionId)
            return@map new
        } ?: emptyList()


        albumListWithBookmarkedData.postValue(newData)
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

    fun addBookmarked(collectionId: Long) {
        viewModelScope.launch {
            albumInteractor.addBookmarked(collectionId)
            bookmarkedAdded.emit(true)
        }
    }

    fun removeBookmarked(collectionId: Long) {
        viewModelScope.launch {
            albumInteractor.removeBookmarked(collectionId)
            bookmarkedRemoved.emit(true)
        }
    }
}
