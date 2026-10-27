# Releasing

To release the library to Maven Central:

1. Edit the root `build.gradle.kts` and remove `-SNAPSHOT` in the `version =` stanza, e.g. "0.1"
2. Edit `README.md`: bump the version in the dependencies under "Using with your project" to match the release.
   Edit `CHANGELOG.md`: replace "unreleased" in the release's heading with today's date, e.g. `## 0.1 — 2026-10-26`
3. Run `./gradlew clean build publish closeAndReleaseStagingRepositories`.
4. (Optional) watch [Maven Central Publishing Deployments](https://central.sonatype.com/publishing/deployments) as the deployment is published.
5. Commit with the commit message of simply being the version being released, e.g. "0.1"
6. git tag the commit with the same tag name as the commit message above, e.g. `0.1`
7. `git push`, `git push --tags`
8. Add the `-SNAPSHOT` back to the `version =` while increasing the version to something which will be released in the future,
   e.g. 0.2-SNAPSHOT, then commit with the commit message "0.2-SNAPSHOT" and push.

Publishing needs `sonatypeUsername`, `sonatypePassword` and the `signing.*` properties in `~/.gradle/gradle.properties`.
