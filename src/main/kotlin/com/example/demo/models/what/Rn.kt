package com.example.demo.models.what

import com.google.gson.annotations.SerializedName
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rns")
@TypeAlias("rn")
data class Rn(
    @SerializedName("id")
    @BsonId val _id: String? = ObjectId().toHexString(),
    val rngsa: String,
    val isFavourite: Boolean?,
    val places: List<Place>?,
    val product_data: ProductData
)