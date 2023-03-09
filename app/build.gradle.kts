import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
val localPropertyFile = project.rootProject.file("local.properties")
if (localPropertyFile.canRead()) {
    properties.load(localPropertyFile.inputStream())
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    kotlin("plugin.serialization") version "1.6.10"
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "org.heet"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "USER_PREFERENCES_NAME", properties.getProperty("USER_PREFERENCES_NAME"))
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "org.heet"
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.lifeycle)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.dagger)
    kapt(libs.bundles.compiler)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.retrofit)
    implementation(libs.coil.core)
    implementation(libs.timber)
    implementation(libs.lottie)
    implementation(libs.okhttp)
    implementation(libs.okhttp.bom)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.kotlin.serialization.converter)
    implementation(libs.image.picker)
    implementation(libs.junit)
}

ktlint {
    android.set(true)
    coloredOutput.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    disabledRules.set(
        setOf(
            "max-line-length",
            "no-wildcard-imports",
            "import-ordering"
        )
    )
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}
