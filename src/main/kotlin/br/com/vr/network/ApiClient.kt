package br.com.vr.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.withContext

suspend fun apiClient(url: String, method: HttpMethod) = withContext(Unconfined) {
    HttpClient(CIO) {
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(JsonFeature){
            serializer = GsonSerializer {
                serializeNulls()
                setPrettyPrinting()
            }
        }
    }.use { client ->
        client.request<HttpResponse> {
            this.url(url)
            this.accept(ContentType.Application.Json)
            this.method = method
        }
    }
}