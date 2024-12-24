package soa.order

import defaultClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import jsonSerializer
import soa.OrderService
import soa.UserServiceClient
import kotlinx.serialization.encodeToString




val httpClient = defaultClient
val userServiceClient = UserServiceClient(httpClient, jsonSerializer)
val service = OrderService(userServiceClient)

fun main() {
    embeddedServer(Netty, port = 8081, host = "127.0.0.1") {
        routing {
            get("/order/{id}") { call.getOrder() }
        }
    }.start(wait = true)
}

private suspend fun ApplicationCall.getOrder() {
    try {
        val id = parameters["id"]
            ?.toIntOrNull()
            ?: return respond(HttpStatusCode.BadRequest, "id must be sent or invalid")
        println("get request with id $id")
        val res = service.getOrder(id)
        respond(HttpStatusCode.OK, jsonSerializer.encodeToString(res))
    } catch (e: Exception) {
        respond(HttpStatusCode.InternalServerError, e.message?: "")
    }
}



