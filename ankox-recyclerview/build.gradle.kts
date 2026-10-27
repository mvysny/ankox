plugins {
    id("ankox.library")
}

description = "Anko's RecyclerView DSL on AndroidX: replaces anko-recyclerview-v7"

android {
    namespace = "org.jetbrains.anko.generated.recyclerviewV7"
}

dependencies {
    api(project(":ankox"))
    api(libs.androidx.recyclerview)
}
