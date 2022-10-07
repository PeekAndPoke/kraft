@file:Suppress("PropertyName")

import Deps.Test.commonTestDeps
import Deps.Test.configureJvmTests
import Deps.Test.jsTestDeps
import Deps.Test.jvmTestDeps

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version Deps.Test.kotest_plugin_version
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
    js(IR) {
        browser {
//            testTask {
//                useKarma {
//                    useChromeHeadless()
//                    usePhantomJS()
//                    useChromiumHeadless()
//                    useFirefoxHeadless()
//                }
//            }
        }
    }

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {

        val commonMain by getting {
            dependencies {
                api(Deps.kotlinx_wrappers_css)
                api(Deps.kotlinx_html)
            }
        }

        val commonTest by getting {
            dependencies {
                commonTestDeps()
            }
        }

        js().compilations["main"].defaultSourceSet {
            dependencies {
            }
        }

        js().compilations["test"].defaultSourceSet {
            dependencies {
                jsTestDeps()
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
            }
        }

        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                jvmTestDeps()
            }
        }
    }
}

tasks {
    configureJvmTests()
}
