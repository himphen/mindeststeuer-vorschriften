package com.example.appstore.ui.main.viewmodel

import com.example.appstore.R
import com.example.appstore.model.Album

class AlbumItemViewModel(
    val item: Album,
) {
    val collectionPrice = "$" + item.collectionPrice.toString()

    val bookmarkIcon = if (item.isBookmarked) {
        R.drawable.ic_bookmark_fill
    } else {
        R.drawable.ic_bookmark
    }
}
