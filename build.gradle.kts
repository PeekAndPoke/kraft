buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm")
    idea

    id("org.jetbrains.dokka") version Deps.dokkaVersion apply false
    id("com.vanniktech.maven.publish") version Deps.mavenPublishVersion apply false
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()

        // KotlinX
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")

        // Repo for KlassIndex (https://github.com/matfax/klassindex)
        maven("https://jitpack.io")

        // Maven Central Snapshot repo s01
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")

        // Snapshots
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies { }

configurations.all {
    idea {
        module {
            isDownloadJavadoc = true
            isDownloadSources = true
        }
    }
}
