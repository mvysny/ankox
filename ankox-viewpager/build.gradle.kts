plugins {
    id("ankox.library")
}

description = "Anko's ViewPager DSL on AndroidX: replaces anko-support-v4"

android {
    namespace = "org.jetbrains.anko.generated.supportV4"
}

dependencies {
    api(project(":ankox"))
    api(libs.androidx.viewpager)
}
