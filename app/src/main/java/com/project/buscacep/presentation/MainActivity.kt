package com.project.buscacep.presentation

import android.icu.text.StringSearch
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.buscacep.R
import com.project.buscacep.data.model.entities.ViaCEPResponse
import com.project.buscacep.presentation.cep.CepViewModel
import kotlinx.coroutines.runBlocking

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
        viewModel.responseLiveData.observe(this) { postJson ->
            address.text = postJson
        }
        viewModel.makeSearch(cepSearch.text.toString())
    }
}