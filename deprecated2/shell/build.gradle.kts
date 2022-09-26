import java.net.URI
import java.io.FileOutputStream

plugins {
    kotlin("multiplatform") version "1.7.10"
}

group = "com.geno1024.gbuilder"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries {
            executable("gbuilder-shell") {
                entryPoint = "com.geno1024.gbuilder.shell.main"
                outputDirectory = File("$rootDir/output")
            }
        }

    }
    sourceSets {
        @Suppress("UNUSED_VARIABLE") val linuxX64Main by getting {
            dependencies {
                implementation(project(":utils"))
            }
        }
    }
}

tasks {
    fun buildCount(key: String) = System.getenv("BUILD_COUNT_SERVER").takeIf { it != null }?.apply {
        try
        {
            val input = URI("$this/$key").toURL().openConnection().getInputStream()
            val output = FileOutputStream(File("$projectDir/build_$key.txt"))
            input.copyTo(output)
        } catch (e: java.net.UnknownHostException)
        {
            System.err.println("Unknown host: $e")
        }
    } ?: File("$projectDir/build_$key.txt").writeText("LOCAL")

    val buildCount = register("buildCount-shell") {
        buildCount("GBuilder-shell")
    }.get()

    getByName("build").dependsOn(buildCount)
}
