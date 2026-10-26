/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("RecyclerviewV7ViewsKt")
package org.jetbrains.anko.recyclerview.v7

import org.jetbrains.anko.custom.*
import org.jetbrains.anko.AnkoViewDslMarker
import android.view.ViewManager
import android.app.Activity
import android.content.Context
import android.widget.*

@PublishedApi
internal object `$$Anko$Factories$RecyclerviewV7ViewGroup` {
    val RECYCLER_VIEW = { ctx: Context -> _RecyclerView(ctx) }
}

inline fun ViewManager.recyclerView(): androidx.recyclerview.widget.RecyclerView = recyclerView() {}

inline fun ViewManager.recyclerView(init: (@AnkoViewDslMarker _RecyclerView).() -> Unit): androidx.recyclerview.widget.RecyclerView {
    return ankoView(`$$Anko$Factories$RecyclerviewV7ViewGroup`.RECYCLER_VIEW, theme = 0) { init() }
}

inline fun Context.recyclerView(): androidx.recyclerview.widget.RecyclerView = recyclerView() {}

inline fun Context.recyclerView(init: (@AnkoViewDslMarker _RecyclerView).() -> Unit): androidx.recyclerview.widget.RecyclerView {
    return ankoView(`$$Anko$Factories$RecyclerviewV7ViewGroup`.RECYCLER_VIEW, theme = 0) { init() }
}

inline fun Activity.recyclerView(): androidx.recyclerview.widget.RecyclerView = recyclerView() {}

inline fun Activity.recyclerView(init: (@AnkoViewDslMarker _RecyclerView).() -> Unit): androidx.recyclerview.widget.RecyclerView {
    return ankoView(`$$Anko$Factories$RecyclerviewV7ViewGroup`.RECYCLER_VIEW, theme = 0) { init() }
}

