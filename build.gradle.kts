import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library") version "9.4.1"
    // AGP 9 compiles Kotlin itself, with the Kotlin it depends on; this line puts our newer one
    // on the classpath instead.
    id("org.jetbrains.kotlin.android") version "2.4.21" apply false
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

defaultTasks("clean", "build")

group = "com.github.mvysny.ankox"
version = "0.1-SNAPSHOT"

android {
    compileSdk = 36
    // Anko's own package, so that R and BuildConfig land where Anko's sources expect them.
    namespace = "org.jetbrains.anko"

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

dependencies {
    // In the API of _RecyclerView, _ViewPager, _CardView and snackbar(). The newest versions still
    // at minSdk 21: this build doesn't check that, the consuming app's manifest merger does.
    api("androidx.recyclerview:recyclerview:1.4.0")
    api("androidx.viewpager:viewpager:1.1.0")
    api("androidx.cardview:cardview:1.0.0")
    api("com.google.android.material:material:1.13.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.group.toString()
            artifactId = "ankox"
            version = project.version.toString()
            pom {
                description = "Anko's Kotlin DSL for Android, ported from the support library to AndroidX"
                name = "Ankox"
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

nexusPublishing {
    repositories {
        // see https://central.sonatype.org/publish/publish-portal-ossrh-staging-api/#configuration
        sonatype {
            nexusUrl = uri("https://ossrh-staging-api.central.sonatype.com/service/local/")
            snapshotRepositoryUrl = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
    }
}

val verifyDesignTripwires = tasks.register<Exec>("verifyDesignTripwires") {
    commandLine("design/verify_design_tripwires.sh")
}
tasks.check { dependsOn(verifyDesignTripwires) }
