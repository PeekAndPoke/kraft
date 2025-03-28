@file:Suppress("PropertyName")

import Deps.Test.commonTestDeps
import Deps.Test.configureJvmTests
import Deps.Test.jsTestDeps
import Deps.Test.jvmTestDeps

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version Deps.Test.kotest_plugin_version
    id("org.jetbrains.kotlin.plugin.serialization") version Deps.kotlinVersion
    id("org.jetbrains.dokka") version Deps.dokkaVersion
    id("com.vanniktech.maven.publish") version Deps.mavenPublishVersion
}

val GROUP: String by project
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            testTask {
//                useKarma {
//                    useChrome()
//                    useChromeHeadless()
//                    useChromiumHeadless()
//                    useChromeCanaryHeadless()
//                    useFirefoxHeadless()
//                }
            }
        }
    }

    jvmToolchain(Deps.jvmTargetVersion)

    jvm {
    }

    sourceSets {
        commonMain {
            dependencies {
                api(Deps.kotlinx_coroutines_core)
                api(Deps.ultra_common)
                api(project(":semanticui"))

                implementation(Deps.IDE.jetbrains_annotations)
            }
        }

        commonTest {
            dependencies {
                commonTestDeps()
            }
        }

        jsMain {
            dependencies {
                api(Deps.kotlinx_coroutines_core_js)
                api(Deps.kotlinx_wrappers_extensions)
                api(Deps.kotlinx_serialization_json)

                // Preact VDOM engine
                api(Deps.Npm { preact() })
                // JWT decode
                api(Deps.Npm { jwtDecode() })
            }
        }

        jsTest {
            dependencies {
                jsTestDeps()
            }
        }

        jvmMain {
        }

        jvmTest {
            dependencies {
                jvmTestDeps()
            }
        }
    }
}

tasks {
    configureJvmTests()
}
