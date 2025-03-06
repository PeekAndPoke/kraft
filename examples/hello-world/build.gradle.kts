@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization") version Deps.kotlinVersion
}

val GROUP = "io.peekandpoke.kraft.examples"
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

repositories {
    mavenCentral()
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

kotlin {
    js {
        browser {
        }

        binaries.executable()
    }

    jvmToolchain(Deps.jvmTargetVersion)

    jvm {
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                // project deps
                api(project(":core"))
            }
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Docs {
    distributeJsExample()
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// https://stackoverflow.com/questions/72731436/kotlin-multiplatform-gradle-task-jsrun-gives-error-webpack-cli-typeerror-c/72731728
// Fixes webpack-cli incompatibility by pinning the newest version.

rootProject.plugins.withType<YarnPlugin> {
    rootProject.extensions.findByType<YarnRootExtension>()?.let { ext ->
//        ext.resolution("webpack-cli", "^4.10.0")
        ext.lockFileName = "examples-${project.name}.yarn.lock"
    }
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
