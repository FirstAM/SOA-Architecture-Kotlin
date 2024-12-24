package soa

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String
)
class UserService {
    fun getUser(id: Int): User {
        return User(id, "Alice")
    }
}