package br.com.vr

import io.ktor.application.*
import br.com.vr.plugins.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureLogging()
    configureSerialization()
}
