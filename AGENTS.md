# Ankox

## What this is

Ankox is the Kotlin DSL of [Anko](https://github.com/Kotlin/anko) 0.10.8, ported from the
Android support library to AndroidX, so that apps built on Anko run without Jetifier, which
AGP 10 removes. It keeps Anko's packages and names: an app swaps the dependency and its sources
compile unchanged. Anko defines the API; Ankox owns the AndroidX port and keeps it building.

## Promises

- **Drop-in for Anko 0.10.8.** Anko's packages, names and JVM file names: an app swaps the dependency and its sources compile unchanged.
- **AndroidX only.** No support library, no Jetifier, in us or in anything we depend on.
- **Old devices are first-class.** minSdk 21; every dependency at the newest version still at minSdk 21.

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
- **A dependency bump keeps minSdk 21.** This build doesn't check it; the consuming app's manifest merger fails instead.

## Module map

- `org.jetbrains.anko` — Anko's commons (dialogs, intents, dimensions, async) and the framework-widget DSL of `anko-sdk15`.
- `org.jetbrains.anko.recyclerview.v7`, `.cardview.v7`, `.support.v4`, `.design` — the DSL for AndroidX widgets, in the packages of the Anko modules they replace.
- `org.jetbrains.anko.sdk15.listeners`, `.custom`, `.internals` — Anko's listener helpers, custom-view hooks and internals.

## Conventions

- **Grows by need.** A declaration an app lacks is copied from Anko 0.10.8's sources and ported, never written anew.
- **Every file keeps its "Derived from Anko 0.10.8" header**, which Apache-2.0 requires of a modified file.
- **Kotlin, Java 17 bytecode, Kotlin 2.4**: a consuming app needs Kotlin 2.3+.
- **One artifact**, `com.github.mvysny.ankox:ankox`, with every AndroidX dependency in its `api`.
- **A change a library user notices gets its `CHANGELOG.md` line in the same commit**, under the unreleased version.

## Commands

- **Run Gradle through `mise exec -- ./gradlew …`**: `mise.toml` pins JetBrains JDK 17.
- `./gradlew` — `clean build`: compile, lint, then `design/verify_design_tripwires.sh` via `check`; what CI runs on push on JDK 17 (`.github/workflows/gradle.yml`).
- `./gradlew publishToMavenLocal` — install the snapshot into `~/.m2` for the apps to try.

## Maintenance of this file

Loaded every turn; cap 34 KB, a module's own `AGENTS.md` 10 KB. Over it, in this order:
delete what has no home — status, history, class lists, what the code already says; trim
each line to its fact plus one clause and send the explanation home — why →
`design/decisions.md`, how across symbols → `design/architecture.md`, how in one symbol →
its doc comment, what upstream does → `design/research.md`; only then a module's own
`AGENTS.md`, peripheral modules first, never the core. Never paraphrase a lazy entry into a
line here. `design/verify_design_tripwires.sh` checks the caps and the cites.
