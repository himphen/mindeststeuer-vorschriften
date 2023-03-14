package com.example.appstore.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appstore.domain.album.AlbumInteractor
import com.example.appstore.model.Album
import com.example.appstore.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val albumInteractor: AlbumInteractor,
) : BaseViewModel() {

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
            it.isBookmarked = bookmarkedIdList.contains(it.collectionId)
            return@map it
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
}
