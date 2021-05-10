plugins {
    kotlin("multiplatform") version "1.4.32"
    id("org.jetbrains.dokka") version "1.4.32"
    `maven-publish`
}

group = "com.github.chosamuel"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {

    js(LEGACY) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    sourceSets {
       val jsMain by getting
        val jsTest by getting
    }
}
