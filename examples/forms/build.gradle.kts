plugins {
    kotlin("js")
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
    implementation(project(":core"))
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

kotlin {
    js(IR) {
        browser {
        }

        binaries.executable()
    }
}
