
# Chip technical test

* The app is written entirely in Kotlin, using Android KTX. Android KTX is a set of Kotlin extensions that optimizes Jetpack and Android platform APIs for more concise and idiomatic Kotlin code.
* The app uses a single Activity with multiple Fragments. Transitions between fragments use the Navigation component and transition animation actions.
* The screens use fragment layouts, created using ConstraintLayout and Data Binding
* On-device data storage of the breeds list are managed with Room at the database level, and surfaced to the UI through ViewModels via LiveData
* AndroidX is used to preserve key app functionality on older versions of Android
* Hilt is used for dependency injection through out the layers of the app
* Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks
* Testing is performed by both local JUnit tests and Espresso Android UI tests

## Libraries Used
* Foundation - Components for core system capabilities, Kotlin extensions and support for multidex and automated testing.
* AppCompat - Degrade gracefully on older versions of Android.
* Android KTX - Write more concise, idiomatic Kotlin code.
* Test - An Android testing framework for unit and runtime UI tests.
* Architecture - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
* Data Binding - Declaratively bind observable data to UI elements.
* Lifecycles - Create a UI that automatically responds to lifecycle events.
* LiveData - Build data objects that notify views when the underlying database changes.
* Flow - A stream of data that can be computed asynchronously.
* Navigation - Handle everything needed for in-app navigation.
* Room - Access your app's SQLite database with in-app objects and compile-time checks.
* ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
* Retrofit for networking 
* Glide for image loading