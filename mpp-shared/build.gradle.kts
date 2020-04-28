import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.3.70"
    id("com.android.library")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
    }
    sourceSets.all{
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}


kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "MppShared"
            }
        }
    }

    android()

    val ktorVersion = "1.3.2"
    val lifecycleVersion = "2.2.0"

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.4")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
        implementation("io.ktor:ktor-client-logging:$ktorVersion")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("io.ktor:ktor-client-android:$ktorVersion")
        implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
        implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    }

    sourceSets["iosMain"].dependencies {
        implementation("io.ktor:ktor-client-ios:$ktorVersion")
        implementation("io.ktor:ktor-client-logging-native:$ktorVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.4")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)
