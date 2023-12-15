package com.example.firstapp.qr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.82:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val ConsumirAPI = retrofit.create(ConsumirAPI::class.java)
}