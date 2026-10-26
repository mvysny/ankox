# Ankox: Anko on AndroidX

Ankox is the Kotlin DSL of [Anko](https://github.com/Kotlin/anko) 0.10.8, ported from the
Android support library to AndroidX, so that apps built on Anko run without Jetifier, which
AGP 10 removes. It keeps Anko's packages and names: an app swaps the dependency and its sources
compile unchanged. Anko defines the API; Ankox owns the AndroidX port and keeps it building.

Anko died before AndroidX: its last release depends on the support library, and its `androidx`
branch stopped in 2018. Ankox is not a complete Anko: it holds the declarations its apps use,
and grows when one needs more.

## Using with your project

Ankox isn't on Maven Central yet. Build it and install it into your local Maven repository:

```sh
./gradlew publishToMavenLocal
```

then add `mavenLocal()` to your app's repositories, and replace every `org.jetbrains.anko:*`
dependency with one:

```groovy
implementation "com.github.mvysny.ankox:ankox:0.1-SNAPSHOT"
```

It replaces `anko-commons`, `anko-sdk15`, `anko-sdk15-listeners`, `anko-support-v4`,
`anko-recyclerview-v7`, `anko-cardview-v7` and `anko-design`; the original artifacts must go,
since they hold the same classes. Ankox brings in AndroidX's `recyclerview`, `viewpager`,
`cardview` and Material Components. It needs minSdk 21 and a Kotlin 2.3+ compiler.

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

builds the library and runs lint and the checks. Releasing: see [RELEASING.md](RELEASING.md).

# License

The Apache License, Version 2.0; see [LICENSE](LICENSE). Ankox is derived from Anko,
Copyright 2016 JetBrains s.r.o.; see [NOTICE](NOTICE).
