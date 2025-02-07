package com.ramirinter.lologin.model
import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("_id") val id: String,
    val title: String,
    val description: String
)