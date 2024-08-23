# Instaleap Challenge Application

## Project Overview

The Instaleap Challenge Application is an Android application designed to facilitate movie searching and management. The app is structured using clean architecture principles, dividing the project into clear, manageable layers, including domain, data, and presentation layers.

## Structure

### Application Layer

- `InstaleapChallengeApplication`: The main application class that initializes the application.

### Domain Layer

- **model**: Contains data models.
    - `Movie`: Data model for representing movies.

### Network Layer

- **exceptions**: Contains custom exceptions for API operations.
    - `ApiErrorException`: Handles API error responses.
    - `NoConnectivityException`: Handles internet connectivity errors.
- **interceptor**: Contains network interceptors.
    - `ConnectivityInterceptor`: Intercepts and checks for network connectivity.
- `MoviesSearchApiService.kt`: Defines the network API calls.

### Data Layer

- **repository.search**: Handles data operations related to movies.
    - `MoviesSearchRepository`: Interface for the repository.
    - `MoviesSearchRepositoryImpl`: Implementation of the repository.

### Use Cases

- `GetMoviesUseCase`: Encapsulates the logic to fetch movies from the repository.

### Framework

- **di**: Dependency injection modules.
    - `NetworkModule.kt`: Provides network-related dependencies.
    - `RepositoryModules.kt`: Provides repository-related dependencies.
    - `ViewModelsModules.kt`: Provides ViewModel-related dependencies.

### Util

- `Constants`: Contains constant values used across the application.
- `Utils`: Contains utility functions.

### Presentation Layer

- **view**: UI components of the application.
    - **actions**: Actions related to movies.
        - `MoviesActions`: Defines actions that can be performed on movies.
    - **activities**: Activity files.
        - `HomeActivity`: The main activity that users interact with.
    - **adapter.viewholder**: Adapters for RecyclerView.
        - `MoviesAdapter.kt`: Adapter for movie lists.
        - `MovieViewHolder`: ViewHolder for movie items.
    - **viewmodels**: ViewModels that manage the UI-related data.
        - `MoviesSearchViewModel`: ViewModel for searching and handling movie data.
