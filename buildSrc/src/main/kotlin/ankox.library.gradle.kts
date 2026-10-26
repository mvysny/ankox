// What every Ankox module shares: an Android library at minSdk 21, published under the module's
// name. A module adds its namespace, its description and its one AndroidX dependency.
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    `maven-publish`
    signing
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
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
