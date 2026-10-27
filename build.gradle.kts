plugins {
    base
    alias(libs.plugins.nexus.publish)
    id("org.jetbrains.kotlinx.kover")
}

dependencies {
    // The coverage report of every module, in the root's `koverHtmlReport`.
    subprojects.forEach { kover(project(it.path)) }
}

defaultTasks("clean", "build")

group = "com.github.mvysny.ankox"
version = "0.1-SNAPSHOT"

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
// A class two modules both ship fails every app that uses both, in D8. No module depends on two
// others, so nothing else in this build would notice.
val verifyNoDuplicateClasses = tasks.register("verifyNoDuplicateClasses") {
    val aars = subprojects.associate { it.name to it.layout.buildDirectory.file("outputs/aar/${it.name}-release.aar").get().asFile }
    dependsOn(subprojects.map { "${it.path}:bundleReleaseAar" })
    inputs.files(aars.values)
    doLast {
        val shippedBy = mutableMapOf<String, MutableList<String>>()
        aars.forEach { (module, aar) ->
            java.util.zip.ZipFile(aar).use { zip ->
                val classesJar = zip.getEntry("classes.jar") ?: return@use
                java.util.zip.ZipInputStream(zip.getInputStream(classesJar)).use { jar ->
                    generateSequence { jar.nextEntry }
                        .filter { it.name.endsWith(".class") }
                        .forEach { shippedBy.getOrPut(it.name) { mutableListOf() } += module }
                }
            }
        }
        val duplicates = shippedBy.filterValues { it.size > 1 }
        check(duplicates.isEmpty()) {
            "Classes shipped by more than one module; give the newer file its own @file:JvmName (D_module_per_dependency):\n" +
                duplicates.entries.joinToString("\n") { (cls, modules) -> "  $cls: ${modules.joinToString()}" }
        }
    }
}
tasks.check { dependsOn(verifyDesignTripwires, verifyNoDuplicateClasses) }
