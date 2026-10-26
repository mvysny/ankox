# Ankox

## What this is

Ankox is the Kotlin DSL of [Anko](https://github.com/Kotlin/anko) 0.10.8, ported from the
Android support library to AndroidX, so that apps built on Anko run without Jetifier, which
AGP 10 removes. It keeps Anko's packages and names: an app swaps the dependency and its sources
compile unchanged. Anko defines the API; Ankox owns the AndroidX port and keeps it building.

## Promises

- **Drop-in for Anko 0.10.8.** Anko's packages, names and JVM file names: an app swaps the dependency and its sources compile unchanged.
- **AndroidX only.** No support library, no Jetifier, in us or in anything we depend on.
- **Old devices are first-class.** minSdk 21, and every dependency declared at the lowest version that works, so the app picks its own.

## Design docs

| File | Owns | Loaded |
|---|---|---|
| `README.md` | the pitch, usage | — |
| `RELEASING.md` | the release procedure | — |
| `CHANGELOG.md` | what a user of the previous release notices, per release | — |
| `NOTICE` | the attribution to Anko that Apache-2.0 requires | — |
| `AGENTS.md` (this) | promises, invariants, the module map, conventions, commands | every turn |
| `design/decisions.md` | why this and not that — `D_` entries, FAQ-shaped | lazy |
| doc comments | what one symbol does and why it is shaped so | at the symbol |

Every fact lives in exactly one of these; the others link to it.

## Invariants

- **Every declaration keeps Anko's package, name, signature and `@file:JvmName`.** A rename breaks the apps' sources, and a JVM name breaks their Java callers (`DimensionsKt`). See `D_anko_fork`.
- **The core module `ankox` depends on no AndroidX library at runtime**; one that needs a library goes to that library's module. See `D_module_per_dependency`.
- **A dependency's version is the lowest that compiles and supports minSdk 21.** A newer one forces every app up; this build doesn't check minSdk, the app's manifest merger does.

## Module map

- `ankox` — `org.jetbrains.anko` (commons, the framework-widget DSL of `anko-sdk15`), `.sdk15.listeners`, `.custom`, `.internals`.
- `ankox-recyclerview`, `ankox-cardview`, `ankox-viewpager`, `ankox-design` — one per AndroidX library, in the packages of the Anko modules they replace.
- `buildSrc` — `ankox.library`, the convention plugin every module applies: Android setup and publishing.

## Conventions

- **Grows by need.** A declaration an app lacks is copied from Anko 0.10.8's sources and ported, never written anew.
- **Every file keeps its "Derived from Anko 0.10.8" header**, which Apache-2.0 requires of a modified file.
- **Kotlin, Java 17 bytecode, Kotlin 2.4**: a consuming app needs Kotlin 2.3+.
- **A module per AndroidX library**, `com.github.mvysny.ankox:ankox-<library>`, its library in `api`; all release together under one version.
- **A change a library user notices gets its `CHANGELOG.md` line in the same commit**, under the unreleased version.
- **Tests are JVM unit tests under Robolectric, never an emulator**, each run on SDK 21 and 35 (`buildSrc/src/robolectric/robolectric.properties`); only the debug variant's.

## Commands

- **Run Gradle through `mise exec -- ./gradlew …`**: `mise.toml` pins JetBrains JDK 17.
- `./gradlew` — `clean build`: compile, lint, unit tests, then `design/verify_design_tripwires.sh` via `check`; what CI runs on push on JDK 17 (`.github/workflows/gradle.yml`).
- `./gradlew :koverHtmlReport` — the coverage of every module, in `build/reports/kover/html`.
- `./gradlew publishToMavenLocal` — install every module's snapshot into `~/.m2` for the apps to try.

## Maintenance of this file

Loaded every turn; cap 34 KB, a module's own `AGENTS.md` 10 KB. Over it, in this order:
delete what has no home — status, history, class lists, what the code already says; trim
each line to its fact plus one clause and send the explanation home — why →
`design/decisions.md`, how across symbols → `design/architecture.md`, how in one symbol →
its doc comment, what upstream does → `design/research.md`; only then a module's own
`AGENTS.md`, peripheral modules first, never the core. Never paraphrase a lazy entry into a
line here. `design/verify_design_tripwires.sh` checks the caps and the cites.
