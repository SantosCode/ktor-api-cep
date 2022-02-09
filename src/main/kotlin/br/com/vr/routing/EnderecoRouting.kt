package br.com.vr.routing

import br.com.vr.model.Endereco
import br.com.vr.network.apiClient
import io.ktor.application.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.enderecoRoute(url: String) {
    route("/v1/cep") {
        get {
            runCatching {
                val cep = call.request.headers["cep"]
                val urlCep = "$url/ws/$cep/json"
                apiClient(urlCep, HttpMethod.Get)
            }.onSuccess { response ->
                val status = response.status
                val endereco = response.receive<Endereco>()
                if (endereco.cep.isEmpty()) return@get call.respond(status,
                    mapOf("message" to "Endereço não encontrado"))
                call.respond(status, endereco)
            }.onFailure { e ->
                when (e) {
                    is ClientRequestException -> call.respond(e.response.status, mapOf("erro" to "CEP inválido"))
                    else -> call.application.log.error("Falha requisição", e)
                }
            }
        }
    }
}