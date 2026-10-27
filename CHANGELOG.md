# Changelog

What a user of the previous release notices, newest release first.

## 0.2 — unreleased

## 0.1 — 2026-10-27

- The first release: the parts of Anko 0.10.8 that PhotoCloud, Aedict and Aedict Reader use, on
  AndroidX. Compared to Anko, `org.jetbrains.anko.*` widgets extend the AndroidX classes, e.g.
  `_RecyclerView` is an `androidx.recyclerview.widget.RecyclerView`.
- One module per AndroidX library, as in Anko: `ankox`, `ankox-recyclerview`, `ankox-cardview`,
  `ankox-viewpager`, `ankox-drawerlayout`, `ankox-swiperefreshlayout`, `ankox-design`, each
  declaring its library at the lowest version that works.
