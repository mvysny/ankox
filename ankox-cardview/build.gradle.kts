plugins {
    id("ankox.library")
}

description = "Anko's CardView DSL on AndroidX: replaces anko-cardview-v7"

android {
    namespace = "org.jetbrains.anko.cardview.v7"
}

dependencies {
    api(project(":ankox"))
    api("androidx.cardview:cardview:1.0.0")
}
