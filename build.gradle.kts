buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath(BuildPlugins.GradleClassPath.androidGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.kotlinGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.hiltGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.navSafeArgs)
        classpath(BuildPlugins.GradleClassPath.kotlinSerialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}




