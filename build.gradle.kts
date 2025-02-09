
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
/*
The apply false in the context of Gradle plugin declarations means that the plugin is available for use but is not applied automatically to any module. This allows you to control where and when the plugin is applied.
 */
    alias(libs.plugins.sqldelight) apply false

    alias(libs.plugins.hiltdagger) apply false



}

