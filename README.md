## SOA (Service-Oriented Architecture)
SOA (Service—oriented architecture) is an approach to building a system where application functionality is divided into independent, self-sufficient services.
Each service is responsible for a specific task or business process and interacts with others through standardized interfaces, most often using protocols like HTTP or messaging.

## Requirements 
- Java 17

See details for: 
- [user-service](user/build.gradle.kts)
- [order-service](user/build.gradle.kts)

Example with three simple services 
- Order
- User
- Notification-Service (not ready)

## How to run 

Run all configurations in IDEA 

- user-service
- order-service

## routes 

*Order*
```shell
curl -v -X GET http://127.0.0.1:8081/order/1
```

*User*
```shell
curl -v -X GET http://127.0.0.1:8082/user/1
```


