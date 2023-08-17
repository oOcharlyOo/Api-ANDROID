package com.example.myapplication

import android.text.Editable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character/{id}")
suspend fun obtenerImagenPersoanje(@Path("id") id: Editable?): Response<ModeloDeDatos>

}