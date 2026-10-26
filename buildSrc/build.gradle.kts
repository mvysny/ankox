plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:9.4.1")
    // AGP 9 compiles Kotlin itself, with the Kotlin it depends on; this line puts our newer one
    // on the classpath instead.
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.21")
}
