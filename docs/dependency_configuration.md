# Dependency Configurations

## Gradle Dependency Configuration

Each module contains its own `build.gradle.kts` to declare the correct build & flavor configurations, and Android project configuration.

All dependency versioning is managed through a single file [`dependencies.kt`][dependencies] to prevent multiple versions of the same dependency from being used and to keep version consistency across all project modules. The file is declared within the `buildSrc` directory for the project in order to reference these configurations within custom Gradle plugins.

When adding a new dependency, it should first be declared under `dependencies.kt`, followed by declaring them within each module where it needs to be compiled.

[dependencies]: ../buildSrc/src/main/java/deps/dependencies.kt
