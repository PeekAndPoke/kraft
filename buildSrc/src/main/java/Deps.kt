import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.TaskContainerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

@Suppress("MemberVisibilityCanBePrivate")
object Deps {
    operator fun invoke(block: Deps.() -> Unit) {
        this.block()
    }

    // //////////////////////////////////////////////////////////////////
    const val kotlinVersion = "1.6.21"
    // //////////////////////////////////////////////////////////////////

    // Publishing //////////////////////////////////////////////////////
    // TODO: Upgrade to 0.15.x -> Beware: configuration changes are necessary
    const val mavenPublishVersion = "0.14.2"

    // https://mvnrepository.com/artifact/org.jetbrains.dokka/dokka-gradle-plugin
    // Dokka gradle plugin org.jetbrains.dokka
    const val dokkaVersion = "1.6.21"
    // //////////////////////////////////////////////////////////////////

    // https://search.maven.org/search?q=g:io.peekandpoke.ultra%20AND%20a:commonmp
    private const val ultra_version = "0.44.4"
    const val ultra_common_mp = "io.peekandpoke.ultra:commonmp:$ultra_version"
    const val ultra_semanticui = "io.peekandpoke.ultra:semanticui:$ultra_version"

//    // https://mvnrepository.com/artifact/io.arrow-kt/arrow-core
//    private const val arrow_core_version = "1.0.1"
//    const val arrow_core = "io.arrow-kt:arrow-core:$arrow_core_version"

    // https://kotlinlang.org/docs/releases.html#release-details
    private const val kotlinx_coroutines_version = "1.6.1"
    const val kotlinx_coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version"

    // https://kotlinlang.org/docs/releases.html#release-details
    private const val kotlinx_serialization_version = "1.3.2"
    const val kotlinx_serialization_core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinx_serialization_version"
    const val kotlinx_serialization_json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_serialization_version"

    // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-extensions
    private const val kotlinx_wrappers_extensions_version = "1.0.1-pre.332-kotlin-1.6.21"
    const val kotlinx_wrappers_extensions =
        "org.jetbrains.kotlin-wrappers:kotlin-extensions:$kotlinx_wrappers_extensions_version"

    // // NPM dependencies /////////////////////////////////////////////////////////////////////////

    // // Test dependencies ////////////////////////////////////////////////////////////////////////

    object Test {

        operator fun invoke(block: Test.() -> Unit) {
            this.block()
        }

        // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
        const val logback_version = "1.2.11"
        const val logback_classic = "ch.qos.logback:logback-classic:$logback_version"

        // https://mvnrepository.com/artifact/io.kotest/kotest-common
        const val kotest_version = "5.3.0"
        const val kotest_assertions_core = "io.kotest:kotest-assertions-core:$kotest_version"
        const val kotest_framework_engine = "io.kotest:kotest-framework-engine:$kotest_version"
        const val kotest_framework_datatest = "io.kotest:kotest-framework-datatest:$kotest_version"

        const val kotest_runner_junit_jvm = "io.kotest:kotest-runner-junit5-jvm:$kotest_version"

        fun KotlinDependencyHandler.commonTestDeps() {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(kotest_assertions_core)
            implementation(kotest_framework_engine)
            implementation(kotest_framework_datatest)
        }

        fun KotlinDependencyHandler.jsTestDeps() {
            implementation(kotest_assertions_core)
            implementation(kotest_framework_engine)
            implementation(kotest_framework_datatest)
        }

        fun KotlinDependencyHandler.jvmTestDeps() {
            implementation(logback_classic)
            implementation(kotest_runner_junit_jvm)
        }

        fun DependencyHandlerScope.jvmTestDeps() {
            testImplementation(logback_classic)
            testImplementation(kotest_runner_junit_jvm)
        }

        fun TaskContainerScope.configureJvmTests(
            configure: org.gradle.api.tasks.testing.Test.() -> Unit = {}
        ) {
            listOfNotNull(
                findByName("test") as? org.gradle.api.tasks.testing.Test,
                findByName("jvmTest") as? org.gradle.api.tasks.testing.Test,
            ).firstOrNull()?.apply {
                useJUnitPlatform { }

                filter {
                    isFailOnNoMatchingTests = false
                }

//                testLogging {
//                    showExceptions = true
//                    showStandardStreams = true
//                    events = setOf(
//                        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
//                        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
//                    )
//                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
//                }

                configure()
            }
        }
    }
}

private fun DependencyHandlerScope.testImplementation(dep: String) =
    add("testImplementation", dep)
