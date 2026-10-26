plugins {
    base
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
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
tasks.check { dependsOn(verifyDesignTripwires) }
