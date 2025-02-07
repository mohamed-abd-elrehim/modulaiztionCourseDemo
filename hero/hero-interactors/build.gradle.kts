apply {
    from("$rootDir/library-build.gradle")
}


dependencies {
    "implementation"(project(":hero:hero-domain"))
    "implementation"(project(":hero:hero-datasource"))
    "implementation"(project(":core"))
    "implementation"(libs.kotlinx.coroutines.android)


}