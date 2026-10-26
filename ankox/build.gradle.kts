plugins {
    id("ankox.library")
}

description = "Anko's commons and framework-widget DSL on AndroidX: replaces anko-commons, anko-sdk15 and anko-sdk15-listeners"

android {
    // Anko's own package, so that R and BuildConfig land where Anko's sources expect them.
    namespace = "org.jetbrains.anko"
}

dependencies {
    // Annotations only, which nothing needs at runtime.
    compileOnly("androidx.annotation:annotation:1.0.1")
}
