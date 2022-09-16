plugins {
    id(BuildPlugins.Apply.androidApplication)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
    id(BuildPlugins.Apply.safeArgsKotlinPlugin)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true

    javacOptions {
        option("-Xmaxerrs", 2000)
    }
}

android {
    compileSdk = AppMetaData.compileSdkVersion
    buildToolsVersion = AppMetaData.buildToolsVersion

    defaultConfig {
        applicationId = AppMetaData.id
        minSdk = AppMetaData.minSdkVersion
        targetSdk = AppMetaData.targetSdkVersion
        versionCode = AppMetaData.versionCode
        versionName = AppMetaData.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common-android"))
    implementation(project(":common-ui"))
    implementation(project(":data-model"))
    implementation(project(":model"))
    implementation(project(":shared"))
    implementation(project(":ui-cheerup"))

    testImplementation(project(":android-test-shared"))
    addUnitTestDependencies()
    androidTestImplementation(project(":android-test-shared"))
    addInstrumentTestDependencies()

    kapt(Libraries.Hilt.compiler)
    implementation(Libraries.Hilt.core)
    implementation(Libraries.multidex)
    implementation(Libraries.AndroidX.splashScreen)
    kapt(Libraries.Hilt.workCompiler)
    implementation(Libraries.Hilt.work)
}
