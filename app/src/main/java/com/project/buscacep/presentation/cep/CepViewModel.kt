package com.project.buscacep.presentation.cep

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.project.buscacep.data.ApiClient
import com.project.buscacep.data.model.entities.ViaCEPResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse

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
                    val postJson = Gson().toJson(post)
                    _responseLiveData.postValue(postJson)
                } else {
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