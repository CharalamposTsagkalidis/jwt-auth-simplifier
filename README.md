#Spring Boot JWT Authentication Example
This is a simple Spring Boot REST API demonstrating how to secure endpoints using JWT.

This project is designed as a beginner-friendly example to help developers understand how to implement a JWT-secured API Gateway using Spring Boot and Spring Security.
It follows a clear and simple structure to make the authentication flow easy to grasp and extend for real-world use cases.

#Features
- Secure endpoints with JWT authentication
- Stateless session management
- Custom JWT filter (`OncePerRequestFilter`)
- Spring Boot & Spring Security-based setup

#Requiered for running the project
1.**Create your own application.properties file**
Make sure to create your own application.properties file in the appropriate directory. This file is required for configuring application-specific settings.
2. **Generate a jwt_secret_key**
You need to generate and configure your own jwt_secret_key for secure JWT token generation. Add the key to your application.properties file to ensure proper authentication.
 
    
