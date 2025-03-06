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
                // addons
                api(project(":addons:avatars"))
                api(project(":addons:browserdetect"))
                api(project(":addons:chartjs"))
                api(project(":addons:konva"))
                api(project(":addons:marked"))
                api(project(":addons:nxcompile"))
                api(project(":addons:pdfjs"))
                api(project(":addons:prismjs"))
                api(project(":addons:signaturepad"))
                api(project(":addons:sourcemappedstacktrace"))
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

rootProject.plugins.withType<YarnPlugin> {
    rootProject.extensions.findByType<YarnRootExtension>()?.let { ext ->
        ext.lockFileName = "examples-${project.name}.yarn.lock"
    }
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
