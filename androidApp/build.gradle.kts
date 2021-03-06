val kotlinCoroutineVersion: String by project
val androidCoreVersion: String by project
val appcompatVersion: String by project
val materialVersion: String by project
val composeVersion: String by project
val navigationComposeVersion: String by project
val lifecycleVersion: String by project
val activityComposeVersion: String by project
val accompanistVersion: String by project
val coilVersion: String by project
val realmVersion: String by project
val koinVersion: String by project
val ktlintVersion: String by project

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.amegane3231.videosearcher.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutineVersion")
    implementation("androidx.core:core-ktx:$androidCoreVersion")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("com.google.accompanist:accompanist-insets:$accompanistVersion")
    implementation("io.coil-kt:coil-compose:$coilVersion")
    implementation("io.realm.kotlin:library-base:$realmVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set(ktlintVersion)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    ignoreFailures.set(true)
}
