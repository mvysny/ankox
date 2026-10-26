plugins {
    id("ankox.library")
}

description = "Anko's ViewPager DSL on AndroidX: replaces anko-support-v4"

android {
    namespace = "org.jetbrains.anko.support.v4"
}

dependencies {
    api(project(":ankox"))
    api("androidx.viewpager:viewpager:1.0.0")
}
