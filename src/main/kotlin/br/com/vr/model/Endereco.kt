package br.com.vr.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Endereco(
    val uf: String = "",
    val complemento: String = "",
    val ddd: String = "",
    val logradouro: String = "",
    val bairro: String = "",
    val localidade: String = "",
    @Transient val ibge: String = "",
    @Transient val siafi: String = "",
    @Transient val gia: String = "",
    val cep: String = "",
)

