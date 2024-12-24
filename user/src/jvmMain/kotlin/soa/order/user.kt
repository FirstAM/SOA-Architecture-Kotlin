package soa.order

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import soa.UserService

private val jsonSerializer = Json {
    encodeDefaults = false
    prettyPrint = true
}

fun main() {
    val service = UserService()
    embeddedServer(Netty, port = 8082, host = "127.0.0.1") {
        routing {
            get("/user/{id}") {
                try {
                    val userId = call.parameters["id"]
                        ?.toIntOrNull()
                        ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid user id")
                    call.respond(HttpStatusCode.OK, jsonSerializer.encodeToString(service.getUser(userId)))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, e.message ?: "")
                }
            }
        }
    }.start(wait = true)
}
