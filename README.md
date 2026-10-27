# Ankox: Anko on AndroidX

[![Maven Central](https://img.shields.io/maven-central/v/com.github.mvysny.ankox/ankox?label=ankox)](https://central.sonatype.com/artifact/com.github.mvysny.ankox/ankox)

Ankox is the Kotlin DSL of [Anko](https://github.com/Kotlin/anko) 0.10.8, ported from the
Android support library to AndroidX, so that apps built on Anko run without Jetifier, which
AGP 10 removes. It keeps Anko's packages and names: an app swaps the dependency and its sources
compile unchanged. Anko defines the API; Ankox owns the AndroidX port and keeps it building.

Anko died before AndroidX: its last release depends on the support library, and its `androidx`
branch stopped in 2018. Ankox is not a complete Anko: it holds the declarations its apps use,
and grows when one needs more.

## Using with your project

Ankox is on Maven Central, which an Android app already lists next to `google()`. Replace the
`org.jetbrains.anko:*` dependencies with their Ankox modules, all of the same version:

```kotlin
dependencies {
    implementation("com.github.mvysny.ankox:ankox:0.1")
    implementation("com.github.mvysny.ankox:ankox-recyclerview:0.1")
}
```

| Anko 0.10.8 | Ankox | Brings in |
|---|---|---|
| `anko-commons`, `anko-sdk15`, `anko-sdk15-listeners` | `ankox` | nothing |
| `anko-recyclerview-v7` | `ankox-recyclerview` | `androidx.recyclerview:recyclerview` 1.0.0+ |
| `anko-cardview-v7` | `ankox-cardview` | `androidx.cardview:cardview` 1.0.0+ |
| `anko-support-v4` (only its `ViewPager` DSL) | `ankox-viewpager` | `androidx.viewpager:viewpager` 1.0.0+ |
| `anko-support-v4` (only `_DrawerLayout`) | `ankox-drawerlayout` | `androidx.drawerlayout:drawerlayout` 1.0.0+ |
| `anko-support-v4` (only `SwipeRefreshLayout.onRefresh`) | `ankox-swiperefreshlayout` | `androidx.swiperefreshlayout:swiperefreshlayout` 1.0.0+ |
| `anko-design` (only `snackbar()`) | `ankox-design` | `com.google.android.material:material` 1.0.0+ |

Each module depends on `ankox`. The original Anko artifacts must all go, since they hold the
same classes. Every AndroidX library is declared at the lowest version that works, so your
app's own, newer version wins. Ankox needs minSdk 21 and a Kotlin 2.3+ compiler.

What changed between releases is in [CHANGELOG.md](CHANGELOG.md).

## Example

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(16)
            textView("Hello, AndroidX")
            recyclerView {
                layoutManager = LinearLayoutManager(context)
            }.lparams(matchParent, matchParent)
        }
    }
}
```

# Developing

```sh
./gradlew
```

builds the library and runs lint and the checks. `./gradlew publishToMavenLocal` installs the
snapshot into your local Maven repository; add `mavenLocal()` to an app's repositories to try it.
Releasing: see [RELEASING.md](RELEASING.md).

# License

The Apache License, Version 2.0; see [LICENSE](LICENSE). Ankox is derived from Anko,
Copyright 2016 JetBrains s.r.o.; see [NOTICE](NOTICE).
