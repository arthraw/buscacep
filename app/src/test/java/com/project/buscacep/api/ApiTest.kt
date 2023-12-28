package com.project.buscacep.api

import com.project.buscacep.data.ApiClient
import com.project.buscacep.data.model.entities.ViaCEPResponse
import com.project.buscacep.data.model.services.ApiService
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ApiTest {
    @Test
    fun `request a address, status code of response`() {
        val cep = "01001000"
        val apiService = ApiClient.create()
        val getAddress = apiService.getPost(cep)
        val getResponse = getAddress.execute()

        val statusCode = getResponse.code()
        assertEquals(200, statusCode)
    }

    @Test
    fun `request a invalid address, status code error`() {
        val cep = "0"
        val apiService = ApiClient.create()
        val getAddress = apiService.getPost(cep)
        val getResponse = getAddress.execute()

        val statusCode = getResponse.code()
        assertEquals(400, statusCode)
    }
}