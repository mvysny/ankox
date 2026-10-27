plugins {
    id("ankox.library")
}

description = "Anko's Material Components helpers (snackbar) on AndroidX: replaces anko-design"

android {
    namespace = "org.jetbrains.anko.generated.design"
}

dependencies {
    api(project(":ankox"))
    api(libs.material)
}
