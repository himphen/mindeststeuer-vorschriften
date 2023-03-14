package com.example.api.response.eta

import com.example.api.model.Album
import com.example.api.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<Album>? = null
) : BaseResponse()
