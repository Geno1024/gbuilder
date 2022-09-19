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
        compilations {
            getByName("main") {
                cinterops {
                    //                    @Suppress("UNUSED_VARIABLE") val libgit2 by creating {
                    //                        packageName = "native.git2"
                    //                    }
                }
            }
        }
        binaries {
            executable("gbuilder-GitShell") {
                entryPoint = "com.geno1024.gbuilder.gitshell.main"
            }
        }
    }
    mingwX64 {
        binaries {
            executable("gbuilder-GitShell") {

            }
        }
    }
    jvm {

    }
    sourceSets {
        val linuxX64Main by getting
        val mingwX64Main by getting
        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
        @Suppress("UNUSED_VARIABLE") val nativeCommonMain by creating {
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

    register("buildCount-GitShell") {
        buildCount("GBuilder-GitShell")
    }.get()
}
