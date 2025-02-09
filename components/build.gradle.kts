ext["namespace"] = "com.example.components"

apply {
    from("$rootDir/android-library-build.gradle")
}


plugins {
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.kotlin.compose)
}


dependencies {
    "implementation"(libs.coil.compose)
}
