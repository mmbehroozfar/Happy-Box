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

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=androidx.paging.ExperimentalPagingApi"
        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    api(project(":common-android"))
    implementation(project(":data-model"))
    implementation(project(":domain"))
    implementation(project(":shared"))

    testImplementation(project(":android-test-shared"))
    addUnitTestDependencies()

    implementation(Libraries.AndroidX.Room.roomPaging)
    kapt(Libraries.AndroidX.Room.compiler)
    implementation(Libraries.AndroidX.ktxCore)
    kapt(Libraries.Hilt.compiler)
    implementation(Libraries.Hilt.core)
}