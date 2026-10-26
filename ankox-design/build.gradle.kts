plugins {
    id("ankox.library")
}

description = "Anko's Material Components helpers (snackbar) on AndroidX: replaces anko-design"

android {
    namespace = "org.jetbrains.anko.design"
}

dependencies {
    api(project(":ankox"))
    api("com.google.android.material:material:1.0.0")
}
