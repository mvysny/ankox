// What every Ankox module shares: an Android library at minSdk 21, published under the module's
// name. A module adds its namespace, its description and its one AndroidX dependency.
import com.android.build.api.variant.HostTestBuilder
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    `maven-publish`
    signing
    id("org.jetbrains.kotlinx.kover")
}

group = rootProject.group
version = rootProject.version

android {
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        // Dependencies are declared at the lowest version that works, on purpose (D_module_per_dependency).
        disable += "GradleDependency"
    }

    testOptions {
        // Robolectric needs the merged resources: Material's themes, the module's own R.
        unitTests.isIncludeAndroidResources = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// AGP 9's `android.sourceSets` accessor is typed by its old API, and fails a cast.
configure<com.android.build.api.dsl.LibraryExtension> {
    sourceSets.getByName("test").resources.directories.add(rootDir.resolve("buildSrc/src/robolectric").path)
}

androidComponents {
    // Release compiles the same code as debug: its unit tests would only run them all twice,
    // in `build` and in Kover's report alike.
    beforeVariants(selector().withBuildType("release")) {
        it.hostTests[HostTestBuilder.UNIT_TEST_TYPE]?.enable = false
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

// A precompiled script plugin gets no `libs` accessor; the catalog is looked up by name instead.
val libs = versionCatalogs.named("libs")

dependencies {
    // Robolectric runs the Android framework on the JVM: unit tests, no emulator.
    "testImplementation"(libs.findLibrary("junit").get())
    "testImplementation"(libs.findLibrary("kotlin-test-junit").get())
    "testImplementation"(libs.findLibrary("robolectric").get())
}

publishing {
    publications {
        register<MavenPublication>("release") {
            artifactId = project.name
            pom {
                name = project.name
                description = provider { project.description }
                url = "https://github.com/mvysny/ankox"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                        distribution = "repo"
                    }
                }
                developers {
                    developer {
                        id = "mavi"
                        name = "Martin Vysny"
                        email = "martin@vysny.me"
                    }
                }
                scm {
                    url = "https://github.com/mvysny/ankox"
                }
            }
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

signing {
    sign(publishing.publications["release"])
}
