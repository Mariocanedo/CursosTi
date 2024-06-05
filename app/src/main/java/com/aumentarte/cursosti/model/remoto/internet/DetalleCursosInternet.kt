package com.aumentarte.cursosti.model.remoto.internet

import com.google.gson.annotations.SerializedName

data class DetalleCursosInternet(
    val id : String,
    val title: String,
    @SerializedName("previewDescription")
    val description: String,
    val image: String,
    val weeks: Int,
    val start : String,
    val tuition : String,
    val minimumSkill :String,
    val scholarshipsAvailabl: Boolean,
    val modality: String
)
