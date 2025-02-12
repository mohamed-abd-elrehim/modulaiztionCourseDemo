ext["namespace"] = "com.example.herodetail"

apply {

    from("$rootDir/android-library-build.gradle")
}
plugins {
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.kotlin.compose)
}


dependencies {

    "implementation"(project(":core"))
    "implementation"(project(":hero:hero-domain"))
    "implementation"(project(":hero:hero-interactors"))
    "implementation"(project(":components"))


}