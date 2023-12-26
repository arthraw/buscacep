package com.project.buscacep.data.model.dao

import com.project.buscacep.data.model.entities.ViaCEPResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("ws/{cep}/json")
    fun getPost(@Path("cep") cep : String) : Call<ViaCEPResponse>

}