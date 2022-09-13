object AppMetaData {
    const val id = "com.mmb.happybox"
    const val compileSdkVersion = 32
    const val targetSdkVersion = 32
    const val minSdkVersion = 21
    const val buildToolsVersion = "33.0.0"
    const val versionCode = 1
    const val versionName = "0.0.1"
}

object BuildPlugins {

    object GradleClassPath {
        const val kotlinVersion = "1.7.10"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

        const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"

        const val hiltGradlePlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Libraries.Hilt.version}"

        const val navSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Libraries.AndroidX.Navigation.version}"

        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    }

    object Apply {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinParcelize = "kotlin-parcelize"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlin = "kotlin"
        const val kotlinxSerialization = "kotlinx-serialization"
        const val daggerHiltPlugin = "dagger.hilt.android.plugin"
        const val safeArgsKotlinPlugin = "androidx.navigation.safeargs.kotlin"
    }
}

object Libraries {
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val material = "com.google.android.material:material:1.6.1"
    const val slider = "com.ncorti:slidetoact:0.9.0"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.5.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val ktxCore = "androidx.core:core-ktx:1.7.0"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val paging = "androidx.paging:paging-runtime:3.1.0"
        const val pagingCommonKtx = "androidx.paging:paging-common-ktx:3.1.0"
        const val splashScreen = "androidx.core:core-splashscreen:1.0.0"

        object Navigation {
            const val version = "2.5.1"
            const val core = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
        }

        object Fragment {
            private const val version = "1.5.2"
            const val core = "androidx.fragment:fragment-ktx:$version"
            const val test = "androidx.fragment:fragment-testing:$version"
        }

        object Room {
            private const val version = "2.4.3"
            const val common = "androidx.room:room-common:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val core = "androidx.room:room-ktx:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val test = "androidx.room:room-testing:$version"
            const val roomPaging = "androidx.room:room-paging:2.4.3"
        }

        object Lifecycle {
            private const val version = "2.5.1"
            const val viweModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }
    }

    object Hilt {
        const val version = "2.43.2"
        const val core = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Kotlin {
        const val jdk =
            "org.jetbrains.kotlin:kotlin-stdlib:${BuildPlugins.GradleClassPath.kotlinVersion}"

        object Coroutine {
            private const val version = "1.6.4"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

}

object TestLibraries {

    private object Versions {
        const val junit4 = "4.13.1"
        const val espresso = "3.3.0"
        const val mockk = "1.11.0"
        const val truth = "1.1"
        const val barista = "4.2.0"
        const val test = "1.2.0"
    }

    const val AndroidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val jUnit = "junit:junit:${Versions.junit4}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val barista = "com.adevinta.android:barista:${Versions.barista}"

    object Espresso {
        const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val idlingResource =
            "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    }

    object Mockk {
        const val android = "io.mockk:mockk-android:${Versions.mockk}"
        const val core = "io.mockk:mockk:${Versions.mockk}"
    }

    object Test {
        const val core = "androidx.test:core-ktx:${Versions.test}"
        const val junit = "androidx.test.ext:junit-ktx:1.1.1"
        const val runner = "androidx.test:runner:${Versions.test}"
        const val rules = "androidx.test:rules:${Versions.test}"
    }
}