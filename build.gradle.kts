buildscript {
    val gradleVersion by extra("7.0.4")
    val kotlinVersion by extra("1.6.10")
    val kotlinSerializationVersion by extra("1.3.2")
    val kotlinCoroutineVersion by extra("1.5.2")
    val androidCoreVersion by extra("1.7.0")
    val appcompatVersion by extra("1.4.0")
    val materialVersion by extra("1.4.0")
    val lifecycleVersion by extra("2.4.0")
    val composeVersion by extra("1.2.0-alpha01")
    val activityComposeVersion by extra("1.4.0")
    val navigationComposeVersion by extra("2.4.0-rc01")
    val accompanistVersion by extra("0.22.0-rc")
    val realmVersion by extra("0.7.0")
    val ktorVersion by extra("1.6.7")
    val koinVersion by extra("3.1.5")
    var reaktiveVersion by extra("1.2.1")
    val junitVersion by extra("4.13.2")
    val junitExtVersion by extra("1.1.3")
    val mockkVersion by extra("1.12.0")
    val espressoVersion by extra("3.4.0")
    val ktlintVersion by extra("0.43.2")
    val ktlintGradleVersion by extra("10.2.1")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:$ktlintGradleVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}