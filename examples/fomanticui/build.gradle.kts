@file:Suppress("PropertyName")

plugins {
    kotlin("js")
    id("org.jetbrains.kotlin.plugin.serialization") version Deps.kotlinVersion
}

val GROUP = "io.peekandpoke.kraft.examples"
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

repositories {
    mavenCentral()
}

dependencies {
    // project deps
    api(project(":core"))
    // addons
    api(project(":addons:prismjs"))
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

kotlin {
    js(IR) {
        browser {
        }

        binaries.executable()
    }
}

apply<ExtractExampleCodePlugin>()

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Docs {
    distributeJsExample()
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
