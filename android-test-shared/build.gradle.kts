plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
}

android {
    compileSdk = AppMetaData.compileSdkVersion
    buildToolsVersion = AppMetaData.buildToolsVersion

    defaultConfig {
        minSdk = AppMetaData.minSdkVersion
        targetSdk = AppMetaData.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(project(":common-android"))
    implementation(project(":shared"))

    kapt(Libraries.Hilt.compiler)
    implementation(Libraries.Hilt.core)
    implementation(Libraries.Hilt.testing)
    implementation(Libraries.AndroidX.Fragment.test)
    implementation(TestLibraries.Espresso.core)
    implementation(TestLibraries.Mockk.android)
    implementation(TestLibraries.Test.runner)
    implementation(Libraries.Kotlin.Coroutine.test)
    implementation(TestLibraries.barista) { exclude("org.jetbrains.kotlin") }
}
