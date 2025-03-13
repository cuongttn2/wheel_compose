import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
    alias(libs.plugins.compose.compiler)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}

room {
    schemaDirectory( "$projectDir/schemas")
}

android {
    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val keyfile = readProperties(keystorePropertiesFile)

    namespace = "com.example.wheelcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.wheelcompose"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = keyfile.getProperty("keyAlias")
            keyPassword = keyfile.getProperty("keyPassword")
            storeFile = rootProject.file(keyfile.getProperty("storeFile"))
            storePassword = keyfile.getProperty("storePassword")
        }
        create("release") {
            keyAlias = keyfile.getProperty("keyAlias")
            keyPassword = keyfile.getProperty("keyPassword")
            storeFile = rootProject.file(keyfile.getProperty("storeFile"))
            storePassword = keyfile.getProperty("storePassword")
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "dimensions"
    productFlavors {
        create("dev") {
            dimension = "dimensions"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "sever_url", "\"https://postman-echo.com/time/now\"")
        }
        create("prod") {
            dimension = "dimensions"
            buildConfigField("String", "sever_url", "\"https://postman-echo.com/time/now\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.service)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.ext.compiler)

    implementation(libs.coil.compose)
    implementation(libs.coil.gift)
    implementation(libs.gson)
    implementation(libs.timber)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor (libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    implementation(libs.sdp)
    implementation(libs.ssp)

    implementation(libs.androidx.cryptoPreferebce)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.androidx.compose.ui.googlefonts)
    implementation(libs.lottie.compose)
//  admob
//    implementation(libs.play.services.ads)
//    implementation(libs.user.messaging.platform)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}