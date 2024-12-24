import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

val DEFAULT_HTTP_TIMEOUT = 60.seconds.inWholeMilliseconds

val jsonSerializer = Json {
    encodeDefaults = true
    prettyPrint = true
}
val defaultClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(jsonSerializer)
    }
    install(Logging) {
        level = LogLevel.ALL
    }
    install(HttpTimeout) {
        requestTimeoutMillis = DEFAULT_HTTP_TIMEOUT
        connectTimeoutMillis = DEFAULT_HTTP_TIMEOUT
        socketTimeoutMillis = DEFAULT_HTTP_TIMEOUT
    }
    this.expectSuccess = false
}