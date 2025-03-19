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
        // Maven Central
        mavenCentral()
        // Maven S01
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
        // Local
//        mavenLocal()
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
