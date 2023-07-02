import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.TaskContainerScope
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

@Suppress("MemberVisibilityCanBePrivate")
object Deps {
    operator fun invoke(block: Deps.() -> Unit) {
        this.block()
    }

    // Kotlin ////////////////////////////////////////////////////////////////////////////////////
    const val kotlinVersion = "1.8.22"
    // ///////////////////////////////////////////////////////////////////////////////////////////

    // JVM ///////////////////////////////////////////////////////////////////////////////////////
    val jvmTarget = JvmTarget.JVM_17
    // ///////////////////////////////////////////////////////////////////////////////////////////

    // Dokka /////////////////////////////////////////////////////////////////////////////////////
    // https://mvnrepository.com/artifact/org.jetbrains.dokka/dokka-gradle-plugin
    // Dokka gradle plugin org.jetbrains.dokka
    const val dokkaVersion = "1.8.20" // kotlinVersion
    // ///////////////////////////////////////////////////////////////////////////////////////////

    // Publishing ////////////////////////////////////////////////////////////////////////////////
    // TODO: Check update to 0.25.x version
    const val mavenPublishVersion = "0.22.0"
    // ///////////////////////////////////////////////////////////////////////////////////////////

    // https://search.maven.org/search?q=g:io.peekandpoke.ultra%20AND%20a:commonmp
    private const val ultra_version = "0.68.0"
    const val ultra_common_mp = "io.peekandpoke.ultra:commonmp:$ultra_version"

    // https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven/org/jetbrains/kotlinx/kotlinx-html/
    private const val kotlinx_html_version = "0.9.0"
    const val kotlinx_html = "org.jetbrains.kotlinx:kotlinx-html:$kotlinx_html_version"

//    private const val peekandpoke_kotlinx_html_version = "0.7.5.2-SNAPSHOT"
//    const val peekandpoke_kotlinx_html = "io.peekandpoke.kotlinx:kotlinx-html:$peekandpoke_kotlinx_html_version"

    // NOTICE: KEEP the pre.450 as newer versions do NOT support JAVA eight anymore
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-css
    private const val kotlinx_wrappers_css_version = "1.0.0-pre.583"
    const val kotlinx_wrappers_css =
        "org.jetbrains.kotlin-wrappers:kotlin-css:$kotlinx_wrappers_css_version"

    // https://kotlinlang.org/docs/releases.html#release-details
    // https://github.com/Kotlin/kotlinx.coroutines/releases
    private const val kotlinx_coroutines_version = "1.7.2"
    const val kotlinx_coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version"
    const val kotlinx_coroutines_core_js =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinx_coroutines_version"

    // https://kotlinlang.org/docs/releases.html#release-details
    // https://github.com/Kotlin/kotlinx.serialization/releases
    private const val kotlinx_serialization_version = "1.5.1"
    const val kotlinx_serialization_core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinx_serialization_version"
    const val kotlinx_serialization_json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_serialization_version"

    // NOTICE: KEEP the pre.450 as newer versions do NOT support JAVA eight anymore
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-extensions
    private const val kotlinx_wrappers_extensions_version = "1.0.1-pre.583"
    const val kotlinx_wrappers_extensions =
        "org.jetbrains.kotlin-wrappers:kotlin-extensions:$kotlinx_wrappers_extensions_version"

    // // NPM dependencies /////////////////////////////////////////////////////////////////////////

    object Npm {
        operator fun <T> invoke(block: Npm.() -> T): T {
            return this.block()
        }

        // https://www.npmjs.com/package/bowser
        fun KotlinDependencyHandler.bowser() = npm("bowser", "2.11.0")

        // TODO: check update to 4.x versions
        // https://www.npmjs.com/package/chart.js
        fun KotlinDependencyHandler.chartJs() = npm("chart.js", "3.9.1")

        // https://www.npmjs.com/package/jwt-decode
        fun KotlinDependencyHandler.jwtDecode() = npm("jwt-decode", "3.1.2")

        // TODO: check update to 9.x versions
        // https://www.npmjs.com/package/konva
        fun KotlinDependencyHandler.konva() = npm("konva", "8.4.3")

        // https://www.npmjs.com/package/minidenticons
        fun KotlinDependencyHandler.minidenticons() = npm("minidenticons", "4.2.0")

        // https://www.npmjs.com/package/marked
        fun KotlinDependencyHandler.marked() = npm("marked", "5.1.0")

        // https://www.npmjs.com/package/@nx-js/compiler-util
        fun KotlinDependencyHandler.nxJsCompilerUtil() = npm("@nx-js/compiler-util", "2.0.0")

        // TODO: check update to 3.x versions
        // https://www.npmjs.com/package/pdfjs-dist
        fun KotlinDependencyHandler.pdfjs_dist() = npm("pdfjs-dist", "2.16.105")

        // https://www.npmjs.com/package/preact
        fun KotlinDependencyHandler.preact() = npm("preact", "10.15.1")

        // https://www.npmjs.com/package/prismjs
        fun KotlinDependencyHandler.prismjs() = npm("prismjs", "1.29.0")

        // https://www.npmjs.com/package/signature_pad
        fun KotlinDependencyHandler.signaturepad() = npm("signature_pad", "4.1.5")

        // https://www.npmjs.com/package/sourcemapped-stacktrace
        fun KotlinDependencyHandler.sourcemappedStacktrace() = npm("sourcemapped-stacktrace", "1.1.11")

        // https://www.npmjs.com/package/trim-canvas
        fun KotlinDependencyHandler.trimCanvas() = npm("trim-canvas", "0.1.2")
    }

    // // Test dependencies ////////////////////////////////////////////////////////////////////////

    object Test {

        operator fun invoke(block: Test.() -> Unit) {
            this.block()
        }

        // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
        const val logback_version = "1.4.8"
        const val logback_classic = "ch.qos.logback:logback-classic:$logback_version"

        // https://mvnrepository.com/artifact/io.kotest/kotest-common
        const val kotest_plugin_version = "5.6.2"
        const val kotest_version = "5.6.2"

        const val kotest_assertions_core = "io.kotest:kotest-assertions-core:$kotest_version"
        const val kotest_framework_api = "io.kotest:kotest-framework-api:$kotest_version"
        const val kotest_framework_datatest = "io.kotest:kotest-framework-datatest:$kotest_version"
        const val kotest_framework_engine = "io.kotest:kotest-framework-engine:$kotest_version"

        const val kotest_runner_junit_jvm = "io.kotest:kotest-runner-junit5-jvm:$kotest_version"

        fun KotlinDependencyHandler.commonTestDeps() {
            kotlin("test-common")
            kotlin("test-annotations-common")
            implementation(kotlinx_coroutines_core)
            implementation(kotest_assertions_core)
            implementation(kotest_framework_api)
            implementation(kotest_framework_datatest)
            implementation(kotest_framework_engine)
        }

        fun KotlinDependencyHandler.jsTestDeps() {
            implementation(kotlinx_coroutines_core_js)
            implementation(kotest_assertions_core)
            implementation(kotest_framework_api)
            implementation(kotest_framework_datatest)
            implementation(kotest_framework_engine)
        }

        fun KotlinDependencyHandler.jvmTestDeps() {
            implementation(logback_classic)
            implementation(kotest_runner_junit_jvm)
            implementation(kotest_assertions_core)
            implementation(kotest_framework_api)
            implementation(kotest_framework_datatest)
            implementation(kotest_framework_engine)
        }

        fun DependencyHandlerScope.jvmTestDeps() {
            testImplementation(logback_classic)
            testImplementation(kotest_runner_junit_jvm)
            testImplementation(kotest_assertions_core)
            testImplementation(kotest_framework_api)
            testImplementation(kotest_framework_engine)
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
