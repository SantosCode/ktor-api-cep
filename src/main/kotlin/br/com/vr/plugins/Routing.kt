package br.com.vr.plugins

import br.com.vr.routing.enderecoRoute
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

val baseUrl = environment.config.property("api.baseURL").getString()
    routing {
        enderecoRoute(baseUrl)
    }
}
