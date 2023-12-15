package com.example.firstapp.qr
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ConsumirAPI {
    @GET("estado")
    fun getEstado(): Call <objeto>
}