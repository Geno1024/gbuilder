import java.net.URI
import java.io.FileOutputStream

plugins {
    kotlin("multiplatform") version "1.7.10"
}

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries {
            sharedLib("gbuilder-utils") {
                outputDirectory = File("$rootDir/output")
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

    val buildCount = register("buildCount-utils") {
        buildCount("GBuilder-utils")
    }.get()

    getByName("build").dependsOn(buildCount)
}
