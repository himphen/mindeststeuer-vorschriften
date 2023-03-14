package com.example.appstore.model

class Album(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
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