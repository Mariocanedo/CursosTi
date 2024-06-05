package com.aumentarte.cursosti.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aumentarte.cursosti.model.local.entidades.DetalleCursosLocal
import com.aumentarte.cursosti.model.local.entidades.ListaCursosLocal

@Database(
    entities = [ListaCursosLocal::class, DetalleCursosLocal::class], version = 1,
    exportSchema = false
)
abstract class CursosBaseDatos : RoomDatabase() {

    abstract fun getCursosDao(): CursosDao

    companion object {
        @Volatile
        private var INSTANCE: CursosBaseDatos? = null

        fun getDataBase(context: Context): CursosBaseDatos {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CursosBaseDatos::class.java,
                    "CASOFUTURO"
                )
                    .build()
                INSTANCE = instance
                return instance
            }

        }

    }
}