plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
    id(BuildPlugins.Apply.kotlinParcelize)
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
    implementation(project(":shared"))
    implementation(project(":model"))
    api(project(":domain-model"))

    testImplementation(project(":android-test-shared"))
    addUnitTestDependencies()

    implementation(Libraries.Hilt.core)
    kapt(Libraries.Hilt.compiler)
    api(Libraries.AndroidX.pagingCommonKtx)
}