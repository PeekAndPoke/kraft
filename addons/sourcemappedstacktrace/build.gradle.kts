import Deps.Test.commonTestDeps
import Deps.Test.configureJvmTests
import Deps.Test.jsTestDeps

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
    /**
     * To find out how to configure the targets, please follZow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    targets {
        js(IR) {
            browser {
            }
        }

//        jvm {
//            compilations.all {
//                kotlinOptions {
//                    verbose = true
//                    jvmTarget = "1.8"
//                }
//            }
//        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core"))
            }
        }

        val commonTest by getting {
            dependencies {
                commonTestDeps()
            }
        }

        val jsMain by getting {
            dependencies {
                api(npm("sourcemapped-stacktrace", "1.1.11"))
            }
        }

        val jsTest by getting {
            dependencies {
                jsTestDeps()
            }
        }

//        val jvmMain by getting {
//        }
//
//        val jvmTest by getting {
//            dependencies {
//                jvmTestDeps()
//            }
//        }
    }
}

tasks {
    configureJvmTests()
}
