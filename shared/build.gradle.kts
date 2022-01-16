val kotlinVersion: String by project
val kotlinSerializationVersion: String by project
val lifecycleVersion: String by project
val realmVersion: String by project
val ktorVersion: String by project
val koinVersion: String by project
val reaktiveVersion: String by project
val junitVersion: String by project
val mockkVersion: String by project

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("io.realm.kotlin") version "0.7.0"
}

version = "1.0"

// iOS is commented out because my environment is Windows
kotlin {
    android()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
                implementation("io.realm.kotlin:library-base:$realmVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("com.badoo.reaktive:reaktive:$reaktiveVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:$junitVersion")
                implementation("io.mockk:mockk:$mockkVersion")
                implementation("androidx.arch.core:core-testing:2.1.0")
            }
        }
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 31
    }
}