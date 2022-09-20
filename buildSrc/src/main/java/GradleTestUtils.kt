import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addUnitTestDependencies() {
    "testImplementation"(Libraries.Kotlin.Coroutine.test)
    "testImplementation"(TestLibraries.truth)
    "testImplementation"(TestLibraries.jUnit)
    "testImplementation"(TestLibraries.Mockk.core)
    "testImplementation"(TestLibraries.turbine)
}

fun DependencyHandlerScope.addInstrumentTestDependencies() {
    "androidTestImplementation"(Libraries.AndroidX.Navigation.test)
    "androidTestImplementation"(Libraries.AndroidX.Fragment.test)
    "androidTestImplementation"(TestLibraries.Test.junit)
    "androidTestImplementation"(TestLibraries.Mockk.android)
    "androidTestImplementation"(TestLibraries.truth)
    "androidTestImplementation"(TestLibraries.jUnit)
}