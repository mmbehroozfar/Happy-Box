plugins {
    id(BuildPlugins.Apply.javaLibrary)
    id(BuildPlugins.Apply.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    addUnitTestDependencies()

    api(Libraries.Kotlin.jdk)
    api(Libraries.Kotlin.Coroutine.core)
    api(Libraries.Kotlin.Coroutine.android)
}
