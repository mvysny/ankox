plugins {
    id("ankox.library")
}

description = "Anko's commons and framework-widget DSL on AndroidX: replaces anko-commons, anko-sdk15 and anko-sdk15-listeners"

android {
    namespace = "org.jetbrains.anko.generated.commons"
}

dependencies {
    // Annotations only, which nothing needs at runtime.
    compileOnly(libs.androidx.annotation)
}
