package com.example.appstore.ui.main

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

    val albumList = MutableLiveData<List<Album>>()

    fun getListFromAPI() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, t -> run {} }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val data = albumInteractor.getAlbumsFromAPI.invoke("Jack Johnson")

            albumList.postValue(data?.map {
                Album.convertFrom(it)
            } ?: emptyList())
        }
    }
}
