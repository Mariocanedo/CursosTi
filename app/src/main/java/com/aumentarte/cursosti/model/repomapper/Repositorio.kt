package com.aumentarte.cursosti.model.repomapper

import android.util.Log
import com.aumentarte.cursosti.model.local.CursosDao
import com.aumentarte.cursosti.model.local.entidades.DetalleCursosLocal
import com.aumentarte.cursosti.model.remoto.CursosRetrofit

class Repositorio(private val cursoDao: CursosDao) {

    private val netService = CursosRetrofit.retrofitInstance()
    val cursosLiveData = cursoDao.getInsertAll()

    suspend fun fetchListCursos() {
        val service = kotlin.runCatching { netService.fetchListaapiInter() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("cursos", it.toString())
                    cursoDao.inserAll(fromInterLista(it)) //from mapper
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }

    }

    suspend fun fetchDetalle(id: String): DetalleCursosLocal? {
        val service = kotlin.runCatching { netService.fetchDetalleapiInter(id) }
        return service.getOrNull()?.body()?.let { detalleCursosInternet ->

            val detalleCursosLocal = fromInterDetalle(detalleCursosInternet)
            cursoDao.inserDetail(detalleCursosLocal)
            detalleCursosLocal
        }
    }
}