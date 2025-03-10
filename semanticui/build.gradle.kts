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
    js {
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

    jvmToolchain(Deps.jvmTargetVersion)

    jvm {
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {

        commonMain {
            dependencies {
                api(Deps.kotlinx_wrappers_css)
                api(Deps.kotlinx_html)
            }
        }

        commonTest {
            dependencies {
                commonTestDeps()
            }
        }

        jsMain {
            dependencies {
            }
        }

        jsTest {
            dependencies {
                jsTestDeps()
            }
        }

        jvmMain {
            dependencies {
            }
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
