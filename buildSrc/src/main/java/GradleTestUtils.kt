import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addUnitTestDependencies() {
    "testImplementation"(Libraries.Kotlin.Coroutine.test)
    "testImplementation"(TestLibraries.truth)
    "testImplementation"(TestLibraries.jUnit)
    "testImplementation"(TestLibraries.Mockk.core)
    "testImplementation"(TestLibraries.turbine)
    "testImplementation"(TestLibraries.Test.archCore)
}

fun DependencyHandlerScope.addInstrumentTestDependencies() {
    "androidTestImplementation"(Libraries.AndroidX.Navigation.test)
    "androidTestImplementation"(Libraries.AndroidX.Fragment.test)
    "androidTestImplementation"(TestLibraries.Test.junit)
    "androidTestImplementation"(TestLibraries.Mockk.android)
    "androidTestImplementation"(TestLibraries.truth)
    "androidTestImplementation"(TestLibraries.jUnit)
    "androidTestImplementation"(TestLibraries.Espresso.core)
    "androidTestImplementation"(TestLibraries.Espresso.contrib) {
        exclude(mapOf("group" to "org.checkerframework"))
    }
    "androidTestImplementation"(Libraries.Hilt.testing)
    "androidTestImplementation"(TestLibraries.Test.runner)
    "androidTestImplementation"(TestLibraries.Test.rules)
    "androidTestImplementation"(TestLibraries.Test.workManager)
    "androidTestImplementation"(TestLibraries.Test.archCore)
    "kaptAndroidTest"(Libraries.Hilt.compiler)
}