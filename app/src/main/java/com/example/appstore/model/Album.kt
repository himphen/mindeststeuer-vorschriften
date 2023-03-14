package com.example.appstore.model

class Album(
    val collectionId: Long,
    val collectionName: String,
    val collectionPrice: Double,
    val imageUrl: String,
    var isBookmarked: Boolean = false
) {


    companion object {
        fun convertFrom(item: com.example.api.model.Album): Album {
            return Album(
                item.collectionId,
                item.collectionName,
                item.collectionPrice,
                item.artworkUrl100
            )
        }
    }
}