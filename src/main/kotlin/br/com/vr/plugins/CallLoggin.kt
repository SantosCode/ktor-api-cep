package br.com.vr.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import org.slf4j.event.Level

fun Application.configureLogging() {
    install(CallLogging) {
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            val host = call.request.local.remoteHost
            val path = call.request.path()
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent Host: $host, Path: $path"
        }
        filter { call -> call.request.path().startsWith("/") }
    }
}