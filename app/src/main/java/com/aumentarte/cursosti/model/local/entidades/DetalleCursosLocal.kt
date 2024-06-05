package com.aumentarte.cursosti.model.local.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_table")
data class DetalleCursosLocal(

    @PrimaryKey
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
