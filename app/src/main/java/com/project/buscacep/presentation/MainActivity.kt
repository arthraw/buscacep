package com.project.buscacep.presentation

import android.icu.text.StringSearch
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.buscacep.R
import com.project.buscacep.presentation.cep.CepViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : CepViewModel
    private lateinit var cepSearch: EditText
    private lateinit var btnCep : Button
    private lateinit var address: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CepViewModel::class.java)
        cepSearch = findViewById(R.id.cep)
        btnCep= findViewById(R.id.searchBtn)
        address = findViewById(R.id.fullAddress)

        btnCep.setOnClickListener {
            showAddress()
        }

    }

    private fun showAddress() {
        val text = viewModel.makeSearch(cepSearch.text.toString())
        val mapAddress = text.execute().body()
        val response = mapOf<String, String?>(
            "CEP" to mapAddress?.cep,
            "Logradouro" to mapAddress?.logradouro,
            "Complemento" to mapAddress?.complemento,
            "Bairro" to mapAddress?.bairro,
            "Numero" to mapAddress?.numero,
            "Localidade" to mapAddress?.localidade,
            "UF" to mapAddress?.uf
        )

        address.text = response.toString()

    }
}