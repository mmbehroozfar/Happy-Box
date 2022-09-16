plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
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

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    api(project(":model"))
    api(project(":domain"))
    api(project(":common-android"))
    implementation(project(":shared"))

    api(Libraries.AndroidX.appCompat)
    api(Libraries.material)
    api(Libraries.AndroidX.constraintLayout)
    api(Libraries.AndroidX.paging)
    api(Libraries.AndroidX.Fragment.core)
    api(Libraries.AndroidX.Navigation.ui)
    api(Libraries.AndroidX.Navigation.core)
    api(Libraries.AndroidX.swipeRefreshLayout)
    api(Libraries.AndroidX.Lifecycle.viewModel)
    api(Libraries.AndroidX.Lifecycle.livedata)
    implementation(Libraries.Kotlin.jdk)
    implementation(Libraries.Hilt.core)
    kapt(Libraries.Hilt.compiler)
}