import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    id(BuildPlugins.Apply.androidTest)
    id(BuildPlugins.Apply.jetbrainsKotlin)
}

android {
    compileSdk = AppMetaData.compileSdkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
        minSdk = 23
        targetSdk = AppMetaData.targetSdkVersion

        testInstrumentationRunner = TestLibraries.AndroidJunitRunner
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }

    testOptions {
        managedDevices {
            devices {
                create("pixel4xlApi31", ManagedVirtualDevice::class) {
                    device = "Pixel 4 XL"
                    apiLevel = 31
                    systemImageSource = "aosp"
                }
            }
        }
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation(TestLibraries.Test.junit)
    implementation(TestLibraries.Espresso.core)
    implementation(TestLibraries.Test.uiAutomator)
    implementation(TestLibraries.Test.macroBenchmark)
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enable = it.buildType == "benchmark"
    }
}