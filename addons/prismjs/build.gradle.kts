import Deps.Test.commonTestDeps
import Deps.Test.configureJvmTests
import Deps.Test.jsTestDeps

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version Deps.Test.kotest_plugin_version
//    id("org.jetbrains.dokka") version Deps.dokkaVersion
    id("com.vanniktech.maven.publish") version Deps.mavenPublishVersion
}

val GROUP: String by project
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

repositories {
    mavenCentral()
}

Docs {
    useEmptyJavadoc()
}

//tasks.register<Copy>("useEmptyJavadoc") {
//    val fromDir = File("").absolutePath
//    val intoDir = File(buildDir, "/libs")
//
//    from(fromDir)
//    into(intoDir)
//
//    include("empty-javadoc.jar")
//    rename("empty-javadoc.jar", "${project.name}-$version-javadoc.jar")
//}

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
//                implementation(Deps.kotlinx_coroutines_core)
                implementation(project(":core"))
            }
        }

        val commonTest by getting {
            dependencies {
                commonTestDeps()
            }
        }

        val jsMain by getting {
            dependencies {
                api(Deps.Npm { prismjs() })
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
