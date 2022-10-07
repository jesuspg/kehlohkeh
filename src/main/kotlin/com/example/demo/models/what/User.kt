package com.example.demo.models.what

import com.google.gson.annotations.SerializedName
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    @SerializedName("_id")
    val id: String
)