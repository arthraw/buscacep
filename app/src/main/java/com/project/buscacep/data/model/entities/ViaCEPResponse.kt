package com.project.buscacep.data.model.entities

data class ViaCEPResponse(
    var cep : String,
    var logradouro : String,
    var complemento: String,
    var bairro : String,
    var localidade : String,
    var uf : String
)
