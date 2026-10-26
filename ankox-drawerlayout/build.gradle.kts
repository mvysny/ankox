plugins {
    id("ankox.library")
}

description = "Anko's DrawerLayout DSL on AndroidX: replaces anko-support-v4's"

android {
    namespace = "org.jetbrains.anko.generated.drawerlayout"
}

dependencies {
    api(project(":ankox"))
    api("androidx.drawerlayout:drawerlayout:1.0.0")
}
