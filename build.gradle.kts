plugins {
    kotlin("jvm") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") // PaperMC Repo
    maven("https://jitpack.io/") // Tap
}

dependencies {
    implementation(kotlin("stdlib")) // Kotlin
    implementation("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT") // Paper
    implementation("com.github.monun:tap:3.4.2") // Tap
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
    }
    shadowJar {
        archiveClassifier.set("dist")
        archiveVersion.set("")
    }
    create<Copy>("dist") {
        from (shadowJar)
        into(".\\")
    }
}