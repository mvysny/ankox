plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.android.gradlePlugin)
    // AGP 9 compiles Kotlin itself, with the Kotlin it depends on; this line puts our newer one
    // on the classpath instead.
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
}
