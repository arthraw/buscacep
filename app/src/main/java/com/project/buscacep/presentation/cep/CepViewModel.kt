package com.project.buscacep.presentation.cep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.project.buscacep.data.ApiClient
import com.project.buscacep.data.model.entities.ViaCEPResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepViewModel : ViewModel()
{
    private val _responseLiveData = MutableLiveData<String>()
    val responseLiveData: LiveData<String> get() = _responseLiveData

    fun makeSearch(cep : String) {
        val apiService = ApiClient.create()
        val call = apiService.getPost(cep)

        call.enqueue(object: Callback<ViaCEPResponse> {
            override fun onResponse(call: Call<ViaCEPResponse>, response: Response<ViaCEPResponse>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    val postJson : String = Gson().toJson(post)
                    val viaCep : ViaCEPResponse = Gson().fromJson(postJson, ViaCEPResponse::class.java)

                    if (viaCep.uf == null) {
                        val postString = buildString {
                            append("Erro: Cep invalido.")
                        }
                        _responseLiveData.postValue(postString)
                    } else {
                        val postString = buildString {
                            append("CEP : ${viaCep.cep}\n")
                            append("Localidade : ${viaCep.localidade}\n")
                            append("Bairro: ${viaCep.bairro}\n")
                            append("Logradouro: ${viaCep.logradouro}\n")
                            append("Complemento: ${viaCep.complemento}\n")
                            append("UF: ${viaCep.uf}")
                        }
                        _responseLiveData.postValue(postString)
                    }
                }
                else if (response.code() == 400) {
                    val postString = buildString {
                        append("Erro: Cep invalido.")
                    }
                    _responseLiveData.postValue(postString)
                }
                else {
                    val postString = buildString {
                        append("Erro: Cep invalido.")
                    }
                    _responseLiveData.postValue(postString)
                    call.cancel()
                }
            }
            override fun onFailure(call: Call<ViaCEPResponse>, t: Throwable) {
                call.cancel()
                throw t
            }
        })
    }
}