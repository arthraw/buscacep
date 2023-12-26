package com.project.buscacep.presentation.cep

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.project.buscacep.data.ApiClient
import com.project.buscacep.data.model.entities.ViaCEPResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepViewModel : ViewModel()
{
    fun makeSearch(cep : String) : Call<ViaCEPResponse> {
        val apiService = ApiClient.create()
        val call = apiService.getPost(cep)

        call.enqueue(object: Callback<ViaCEPResponse> {
            override fun onResponse(call: Call<ViaCEPResponse>, response: Response<ViaCEPResponse>) {
                if (response.isSuccessful) {
                    val post = response.body()

                    println(Gson().toJson(post))
//                    post?.apply {
//
//                    }
                    Gson().toJson(post)
                } else {
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<ViaCEPResponse>, t: Throwable) {
                call.cancel()
                throw t
            }
        })
        return call
    }



}