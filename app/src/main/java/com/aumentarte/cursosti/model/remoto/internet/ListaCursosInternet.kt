package com.aumentarte.cursosti.model.remoto.internet

import com.google.gson.annotations.SerializedName

data class ListaCursosInternet(

    val id : String,
    val title: String,
 // @SerializedName("previewDescription")
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val start : String,
)
