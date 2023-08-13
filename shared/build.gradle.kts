plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqldelight.runtime)
                implementation(libs.jetbrains.kotlinx.datetime)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.android.driver)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.native.driver)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

sqldelight {
    database("NotesDatabase") {
        packageName = "com.lesniak.kmmnotes.db"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.lesniak.kmmnotes"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}