plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    viewBinding.isEnabled = true

    defaultConfig {
        applicationId = "com.example.mpp.mobile"
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes["release"].apply {
        isMinifyEnabled = true
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    packagingOptions {
        pickFirst("META-INF/kotlinx-io.kotlin_module")
        pickFirst("META-INF/kotlinx-coroutines-io.kotlin_module")
        pickFirst("META-INF/ktor-client-serialization.kotlin_module")
        pickFirst("META-INF/ktor-http.kotlin_module")
        pickFirst("META-INF/ktor-utils.kotlin_module")
        pickFirst("META-INF/kotlinx-serialization-runtime.kotlin_module")
        pickFirst("META-INF/ktor-io.kotlin_module")
        pickFirst("META-INF/ktor-http-cio.kotlin_module")
        pickFirst("META-INF/ktor-client-json.kotlin_module")
        pickFirst("META-INF/ktor-client-core.kotlin_module")
        pickFirst("META-INF/ktor-client-logging.kotlin_module")
    }

    kotlinOptions{
        jvmTarget = "1.8"
    }
}

dependencies {

    //modules
    implementation(project(":mpp-shared"))

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${rootProject.extra.get("kotlinVersion") as String}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")

    //appcompat
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.android.material:material:1.1.0")

    //koin
    implementation("org.koin:koin-android:${rootProject.extra.get("koinVersion") as String}")
    implementation("org.koin:koin-androidx-viewmodel:${rootProject.extra.get("koinVersion") as String}")

    //test
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
