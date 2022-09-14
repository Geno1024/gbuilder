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
            executable("gbuilder") {
                entryPoint = "com.geno1024.builder.main"
            }
        }
    }
    mingwX64 {
        binaries {
            executable("gbuilder") {
                entryPoint = "com.geno1024.builder.main"
            }
        }
    }
    jvm {

    }
    sourceSets {
        val linuxX64Main by getting
        val mingwX64Main by getting
        @Suppress("UNUSED_VARIABLE") val gitServerCommonMain by creating {
            linuxX64Main.dependsOn(this)
            mingwX64Main.dependsOn(this)
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

    register("buildCount") {
        buildCount("gbuilder")
    }.get()
}
