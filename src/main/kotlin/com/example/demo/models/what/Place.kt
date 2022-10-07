package com.example.demo.models.what

import com.google.gson.annotations.SerializedName

data class Place(
    val place_api_data: PlaceData?,
    val price: String?
)