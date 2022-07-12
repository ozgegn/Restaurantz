<h1 align="center">Restaurantz</h1>

<p align="center">  
 Restaurantz demonstrates modern Android development with Hilt, Coroutines, Jetpack (Room, ViewModel) and Compose based on MVVM architecture.
</p>
</br>

## Tech stack
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
    - Lifecycle - Observe Android lifecycles
    - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - Room Persistence - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI.
- Architecture
    - MVVM Architecture
    - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
-
## Architecture
Restaurantz is based on the MVVM architecture and the Repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)

## Open API

Using the [RandomApi](https://random-data-api.com/) for constructing RESTful API.<br>
