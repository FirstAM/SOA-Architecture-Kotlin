package soa

import kotlinx.serialization.Serializable
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

@Serializable
data class Order(
    val orderId: Int = 1,
    val userId: Int = 1,
    val userName: String = "Alice"
)

@Serializable
data class User(
    val id: Int,
    val name: String
)

class UserServiceClient(
    private val httpClient: HttpClient,
    private val serializer: Json,
    private val host: String = "http://127.0.0.1:8082"
) {
    suspend fun getUser(id: Int): User  {
      val response =  httpClient.get("$host/user/$id")
        return serializer.decodeFromString<User>(response.bodyAsText())
    }
}

class OrderService(
    private val userClient: UserServiceClient
) {
    suspend fun getOrder(id: Int): Order {
        val user: User = userClient.getUser(id)
        return Order(orderId = id, user.id, user.name)
    }
}