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
}
