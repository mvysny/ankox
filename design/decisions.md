# Decisions

Why this project is the way it is and not otherwise — FAQ-shaped: each entry is a question and
its current answer. Rewrite the answer when it changes; delete the entry when nobody asks any
more. An entry is earned by what it would cost to reverse — half the code base — or by research
the next person would otherwise redo (cited as its `R_`). Not an entry: windows → panels
"because that's the trend", this red over that red, `get_foo` over `is_foo?`, the testing library,
the CI host, a version bump — a comment at the site of the choice, or nothing; nothing about
`design/` itself. Cite by slug, `D_<slug>`, never by position; `grep '^## D_' design/decisions.md`
is the index. The first entry is the ruler: every later one trims to its length — which is how
long this file gets, so keep it short. When you have written an entry, re-read it against the one
above, open the doc comments it touches and cut what they already say, then cut the fat.

---

## D_anko_fork — Why a copy of Anko in Anko's packages rather than Jetifier, Compose or Splitties?

PhotoCloud, Aedict and Aedict Reader build their UI with Anko 0.10.8, which depends on the
support library, and every Google Play Billing from 6.1 on depends on AndroidX; the two can't
share an app. Anko died before AndroidX, and no fork is maintained. So Ankox holds the Anko
declarations the apps use, copied from its Apache-2.0 sources and ported to AndroidX, with
Anko's packages and `@file:JvmName`s, so the apps' sources compile unchanged. Why not Jetifier:
Anko's Kotlin metadata kept support-library signatures that compiled and then died with
`NoSuchMethodError`, and AGP 10 removes Jetifier. Why not Compose: a rewrite of every screen in
three maintenance-only apps, and Compose 1.10 needs minSdk 23. Why not Splitties: a different
API, so a rewrite too, and dormant since 2021. Why not our own packages: every import in three
apps rewritten for nothing. The cost we carry: the classes clash with the original
`org.jetbrains.anko:*` artifacts, so an app drops all of them at once.
