apply<ExtractExampleCodePlugin>()

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

    sourceSets {
        main {
            kotlin.srcDir("build/generated/extracted-code-blocks")
        }
    }
}


// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// https://stackoverflow.com/questions/72731436/kotlin-multiplatform-gradle-task-jsrun-gives-error-webpack-cli-typeerror-c/72731728
// Fixes webpack-cli incompatibility by pinning the newest version.
//rootProject.plugins.withType<YarnPlugin> {
//    rootProject.extensions.findByType<YarnRootExtension>()?.let { ext ->
//        ext.resolution("webpack-cli", "^4.10.0")
//    }
//}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
