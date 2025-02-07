apply {
    from("$rootDir/library-build.gradle")
}


plugins {
    kotlin("plugin.serialization") version "2.0.0"
}


dependencies {
    "implementation"(project(":hero:hero-domain"))
    "implementation"(libs.ktor.core)
    "implementation"(libs.ktor.client.json)
    "implementation"(libs.ktor.serialization)
    "implementation"(libs.ktor.client.cio)
    //ktor-client-content-negotiation It allows automatic
    // serialization and deserialization of the request and response data in
    // different formats such as JSON, XML, etc., based on the Content-Type header.

    "implementation"(libs.ktor.client.content.negotiation)


    // Kotlinx Serialization is a powerful library for serializing and
    // deserializing objects in Kotlin. Its primary job is to convert Kotlin objects
    // into a format (like JSON, ProtoBuf, or others) that can be easily stored or transmitted
    // , and then later convert that data back into Kotlin objects.

    "implementation"(libs.kotlinx.serialization.json)
    "testImplementation"(libs.ktor.mock) // `mock` is usually used for testing


    /*
    Summary of the Relationship Between the Libraries:
    Core Ktor Client (ktor-core): Handles basic HTTP requests and responses.
    CIO Engine (ktor-client-cio): Provides the engine to handle low-level network communication,
     working asynchronously with Kotlin coroutines.
    Content Negotiation (ktor-client-content-negotiation): Determines how to handle the serialization
     and deserialization of HTTP content based on the response's Content-Type.
    JSON Handling (ktor-client-json, kotlinx.serialization.json): Provides tools for serializing and deserializing
    JSON data. The Ktor client uses Kotlinx Serialization to map JSON to and from Kotlin objects.
    Serialization Plugin (ktor-serialization): Integrates Kotlinx Serialization with Ktor to handle all the
    (de)serialization for the HTTP client.Mocking for Testing (ktor-mock): Helps in
    testing by simulating network requests and responses, so you don't need to make real API calls in unit tests.

    * */

    /*Ktor and Retrofit are both popular libraries for making HTTP requests in Kotlin, but they serve different purposes and have different approaches. Here’s a detailed comparison of Ktor vs Retrofit:

1. General Overview:
Ktor:
Type: Full-featured HTTP client and server framework for Kotlin.
Creator: Developed by JetBrains (the creators of Kotlin).
Purpose: Ktor is designed to work with both client and server applications. The Ktor HTTP client focuses on being lightweight, flexible, and asynchronous. It’s great for making network requests in Kotlin-based applications, especially when you need fine-grained control over the networking logic and configuration.
Main Strengths: Extensible, coroutine-based, and allows deep customization for networking operations.
Retrofit:
Type: Type-safe HTTP client for Android and Java.
Creator: Developed by Square (the same company behind OkHttp).
Purpose: Retrofit is focused on simplifying the creation and management of HTTP requests. It’s a type-safe HTTP client that uses annotations to automatically generate HTTP request code. It abstracts away much of the complexity of handling API calls and responses.
Main Strengths: Simplicity, ease of use, and strong integration with OkHttp and Gson/Moshi for JSON parsing.

Setup and Ease of Use:
Ktor:
Setup: Ktor requires you to set up your HTTP client and request/response handling manually. You have to install the necessary plugins (for JSON handling, content negotiation, etc.) and handle serialization/deserialization (usually through Kotlinx Serialization).
Learning Curve: Ktor is more flexible but requires more initial setup and understanding of how to configure the client, plugins, and serialization logic.
Example:
kotlin
Copy
Edit
val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json)
    }
}

val response: ApiResponse = client.get("https://api.example.com/endpoint")
Retrofit:
Setup: Retrofit uses annotations to declare how network requests should be structured. You only need to define an interface for the API and Retrofit automatically generates the network request logic.
Learning Curve: Retrofit is easier to get started with, especially for developers who are familiar with REST APIs. It abstracts much of the complexity, reducing the boilerplate.
Example:
kotlin
Copy
Edit
interface ApiService {
    @GET("endpoint")
    suspend fun getData(): ApiResponse
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
val response = apiService.getData()
3. Flexibility and Control:
Ktor:
Flexibility: Ktor is extremely flexible and gives you complete control over how network requests are handled, which is great for applications that need to customize behavior (e.g., custom authentication, interceptors, headers, etc.).
Customization: Ktor allows you to add custom features like content negotiation, logging, authentication, retry logic, etc., without restrictions.
Async Programming: Ktor is built with Kotlin Coroutines in mind, meaning you can take full advantage of Kotlin's native support for asynchronous programming. It’s built for asynchronous requests, making it ideal for modern Kotlin apps.
Retrofit:
Flexibility: Retrofit is less flexible compared to Ktor. While it’s good at automating common tasks (like making network requests), it can be harder to customize without doing workarounds or creating custom converters.
Customization: Retrofit uses OkHttp under the hood, so it’s possible to customize request handling by creating custom interceptors, but the library itself doesn't give as much low-level control as Ktor.
Async Programming: Retrofit supports suspend functions (from Kotlin Coroutines), making it easy to work with async code.
4. Integration and Compatibility:
Ktor:
JSON Serialization: Ktor requires additional setup for JSON serialization (via Kotlinx Serialization or other libraries like Gson or Moshi).
Other Features: Ktor is not limited to just HTTP; it also provides tools for building HTTP servers, WebSockets, and more. This makes Ktor a better choice for building both client-side and server-side applications.
Retrofit:
JSON Serialization: Retrofit integrates seamlessly with Gson or Moshi for JSON parsing, which simplifies serialization and deserialization tasks. It can be extended to support other formats.
Other Features: Retrofit is focused on HTTP requests and doesn’t have built-in support for WebSockets or HTTP server functionality.
5. Performance:
Ktor:
Performance: Ktor is highly optimized for performance. It uses CIO (Coroutine-based I/O) engine to handle asynchronous networking tasks, which makes it lightweight and fast.
Scalability: Since Ktor is more low-level and gives you more control over the network operations, you can optimize your app’s networking for performance better.
Retrofit:
Performance: Retrofit is built on top of OkHttp, which is fast and efficient for most HTTP operations. However, Retrofit doesn’t have as fine-grained control over the network stack as Ktor, so you may need to rely on OkHttp for optimization.
6. Community Support and Ecosystem:
Ktor:
Community Support: Ktor has a smaller user base compared to Retrofit, but it is growing steadily. It’s actively maintained by JetBrains, and it has strong documentation.
Ecosystem: Ktor has a wider ecosystem since it's not just an HTTP client but a full framework for HTTP servers and clients. However, its ecosystem is more focused on Kotlin-specific technologies.
Retrofit:
Community Support: Retrofit has a large and well-established community, especially in the Android development space.
Ecosystem: Retrofit works seamlessly with the OkHttp library (for making requests), Gson or Moshi (for JSON parsing), and other common Android libraries. Retrofit has strong integration with the Android ecosystem, which is a huge advantage if you’re building an Android app.
7. Testing:
Ktor:
Testing: Ktor has built-in support for mocking HTTP requests during testing, which allows you to test your network-related code without needing a real server (via the ktor-mock library).
Unit Testing: You can mock responses, configure custom headers, and more. The flexibility in Ktor gives you complete control over test environments.
Retrofit:
Testing: Retrofit doesn’t provide built-in mocking support, but you can use libraries like MockWebServer (from OkHttp) to mock HTTP requests for testing purposes. Retrofit integrates seamlessly with JUnit and other testing frameworks.
Unit Testing: Retrofit makes it easy to write unit tests for your network code, but you have to set up external tools for mocking or simulate responses manually.
When to Use Ktor vs Retrofit:
Use Ktor if:
You need fine-grained control over HTTP requests and responses.
You want flexibility in customizing networking (e.g., custom authentication, retries, advanced content negotiation).
You are building a Kotlin server-side app or an Android app that requires advanced networking functionality.
You want a Kotlin-native library that integrates well with Kotlin Coroutines for asynchronous programming.
Use Retrofit if:
You need a simple, type-safe HTTP client with minimal setup.
You are working with Android or Java and want an easy, annotation-based approach to making network calls.
You are focused on REST APIs and want seamless integration with Gson, Moshi, or other serialization libraries.
You prefer simplicity over fine-grained control and need to get up and running quickly.
Conclusion:
Ktor is a more flexible and low-level framework suitable for developers who want full control over the networking process and prefer Kotlin Coroutines for async operations.
Retrofit is a higher-level, easy-to-use library that abstracts away much of the complexity of HTTP networking and is ideal for REST API consumers, especially in Android apps.
Your choice depends on the complexity of your project and the level of control you need over the HTTP interactions.









    */
}