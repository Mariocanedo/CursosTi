package com.aumentarte.cursosti.model.local.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "list_table")
data class ListaCursosLocal(

    @PrimaryKey
    val id : String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val start : String,
)
