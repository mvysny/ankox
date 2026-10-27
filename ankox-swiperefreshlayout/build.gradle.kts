plugins {
    id("ankox.library")
}

description = "Anko's SwipeRefreshLayout listener on AndroidX: replaces anko-support-v4's"

android {
    namespace = "org.jetbrains.anko.generated.swiperefreshlayout"
}

dependencies {
    api(project(":ankox"))
    api(libs.androidx.swiperefreshlayout)
}
