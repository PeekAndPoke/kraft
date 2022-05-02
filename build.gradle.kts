buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    idea
    kotlin("jvm")

    id("org.jetbrains.dokka") version Deps.dokkaVersion apply false
    id("com.vanniktech.maven.publish") version Deps.mavenPublishVersion apply false
}

allprojects {
    repositories {
        mavenCentral()

        // Maven Central Snapshot repo s01
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")

        // KotlinX
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")

        // Repo for KlassIndex (https://github.com/matfax/klassindex)
        maven("https://jitpack.io")
    }
}

dependencies { }
