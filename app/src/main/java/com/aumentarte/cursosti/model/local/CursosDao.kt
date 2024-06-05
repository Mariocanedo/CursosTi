package com.aumentarte.cursosti.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aumentarte.cursosti.model.local.entidades.DetalleCursosLocal
import com.aumentarte.cursosti.model.local.entidades.ListaCursosLocal

@Dao
interface CursosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(listaCurso : List<ListaCursosLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserDetail(detalle: DetalleCursosLocal)

    @Query("SELECT * FROM list_table ORDER BY id ASC")
    fun getInsertAll() : LiveData<List<ListaCursosLocal>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getDetail(id: String) : LiveData<DetalleCursosLocal>

}