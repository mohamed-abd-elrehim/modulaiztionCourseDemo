
apply {
    from("$rootDir/library-build.gradle")
}


dependencies {
    "implementation"(project(":hero:hero-domain"))
    "implementation"(libs.ktor.android)
    "implementation"(libs.ktor.core)
    "implementation"(libs.ktor.serialization)
    "testImplementation"(libs.ktor.mock) // `mock` is usually used for testing



}