import Deps.Test.commonTestDeps
import Deps.Test.configureJvmTests
import Deps.Test.jsTestDeps
import Deps.Test.jvmTestDeps

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version Deps.Test.kotest_version
    id("org.jetbrains.kotlin.plugin.serialization") version Deps.kotlinVersion
    id("org.jetbrains.dokka") version Deps.dokkaVersion
}

val GROUP: String by project
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

repositories {
    mavenCentral()
}

kotlin {
    /**
     * To find out how to configure the targets, please follow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    targets {
        js(IR) {
            browser {
                testTask {
                    useKarma {
//                        useChrome()
                        useChromeHeadless()
//                        useChromiumHeadless()
//                        useChromeCanaryHeadless()
//                        useFirefoxHeadless()
                    }
                }
            }
        }

        jvm {
            compilations.all {
                kotlinOptions {
                    verbose = true
                    jvmTarget = "1.8"
                }
            }
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.kotlinx_coroutines_core)
                api(Deps.ultra_common_mp)
            }
        }

        val commonTest by getting {
            dependencies {
                commonTestDeps()
            }
        }

        val jsMain by getting {
            dependencies {
                api(Deps.kotlinx_wrappers_extensions)
                api(Deps.ultra_semanticui)

                // External Javascript libraries
                api(npm("preact", "10.5.14"))
                // Preact VDOM engine
                api(npm("preact", "10.5.14"))
                // JWT
                api(npm("jwt-decode", "2.2.0"))
                // External Javascript libraries
                api(npm("sourcemapped-stacktrace", "1.1.11"))
                // Markdown to HTML
                api(npm("marked", "1.1.0"))
                // Javascript sandbox
                api(npm("@nx-js/compiler-util", "2.0.0"))
                // Chart.js
                api(npm("chart.js", "3.6.0"))

            }
        }

        val jsTest by getting {
            dependencies {
                jsTestDeps()
            }
        }

        val jvmMain by getting {
        }

        val jvmTest by getting {
            dependencies {
                jvmTestDeps()
            }
        }
    }
}

tasks {
    configureJvmTests()
}

apply(from = "./../maven.publish.gradle.kts")
