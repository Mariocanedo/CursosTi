package com.aumentarte.cursosti.model.remoto

import com.aumentarte.cursosti.model.remoto.internet.DetalleCursosInternet
import com.aumentarte.cursosti.model.remoto.internet.ListaCursosInternet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CursosApi {

    @GET("courses")
    suspend fun fetchListaapiInter() : Response<List<ListaCursosInternet>>

    @GET("courses/{id}")
    suspend fun fetchDetalleapiInter(@Path("id") id:String): Response<DetalleCursosInternet>
}