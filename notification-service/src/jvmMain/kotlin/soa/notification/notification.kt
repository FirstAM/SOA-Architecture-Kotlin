package soa.notification

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable


fun main() {
    embeddedServer(Netty, port = 8083, host = "127.0.0.1") {
        routing {
            get("/notification") { call.sendNotification() }
        }
    }.start(wait = true)
}

@Serializable
data class NotificationResponse(
    val status: String
)

private suspend fun ApplicationCall.sendNotification() {
    val response = NotificationResponse("Notification sent to Alice" )
    respond(HttpStatusCode.OK, response)
}