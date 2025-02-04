package com.ramirinter.lologin.model
import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("_id") val id: String,  // El `_id` de MongoDB es mapeado a `id`
    val title: String,
    val description: String
)