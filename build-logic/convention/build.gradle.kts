import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.yugyd.idiomatic.android.gradle.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.update.versions.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "hida.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "hida.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("composeAndroidApplication") {
            id = "hida.android.compose.application"
            implementationClass = "ComposeAndroidApplicationConventionPlugin"
        }
        register("composeAndroidLibrary") {
            id = "hida.android.compose.library"
            implementationClass = "ComposeAndroidLibraryConventionPlugin"
        }
        register("hiltAndroid") {
            id = "hida.android.hilt"
            implementationClass = "HiltAndroidConventionPlugin"
        }
        register("dependencyUpdates") {
            id = "hida.dependency.updates"
            implementationClass = "DependencyUpdatesPlugin"
        }
    }
}
