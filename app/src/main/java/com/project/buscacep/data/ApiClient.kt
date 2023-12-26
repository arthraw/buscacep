package com.project.buscacep.data

import com.project.buscacep.data.model.dao.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        // Api publica do Via Cep
        private const val BASE_URL = "https://viacep.com.br/"

        /*
        * @return: Retorna um build da nossa ApiService utilizando retrofit e OkHttpClient e coleta os dados em um JSON.
        */
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .callTimeout(10, TimeUnit.SECONDS)
                        .build()
                )
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}