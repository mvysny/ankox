pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

// Not "ankox": that's the core module, and a root of the same name and group clashes with it.
rootProject.name = "ankox-parent"

include("ankox", "ankox-recyclerview", "ankox-cardview", "ankox-viewpager", "ankox-design")
