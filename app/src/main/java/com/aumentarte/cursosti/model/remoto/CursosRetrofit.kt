package com.aumentarte.cursosti.model.remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CursosRetrofit {
    companion object {
        private const val BASE_URL = "https://caso-invest-center-mariocanedo.vercel.app/"
        lateinit var retrofitInstance: Retrofit

        fun retrofitInstance(): CursosApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CursosApi::class.java)
        }
    }
}