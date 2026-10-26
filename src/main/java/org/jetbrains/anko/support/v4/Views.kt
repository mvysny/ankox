/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("SupportV4ViewsKt")
package org.jetbrains.anko.support.v4

import org.jetbrains.anko.custom.*
import org.jetbrains.anko.AnkoViewDslMarker
import android.view.ViewManager
import android.app.Activity
import android.content.Context
import android.widget.*

@PublishedApi
internal object `$$Anko$Factories$SupportV4View` {
}

@PublishedApi
internal object `$$Anko$Factories$SupportV4ViewGroup` {
    val VIEW_PAGER = { ctx: Context -> _ViewPager(ctx) }
}

inline fun ViewManager.viewPager(): androidx.viewpager.widget.ViewPager = viewPager() {}

inline fun ViewManager.viewPager(init: (@AnkoViewDslMarker _ViewPager).() -> Unit): androidx.viewpager.widget.ViewPager {
    return ankoView(`$$Anko$Factories$SupportV4ViewGroup`.VIEW_PAGER, theme = 0) { init() }
}

inline fun Context.viewPager(): androidx.viewpager.widget.ViewPager = viewPager() {}

inline fun Context.viewPager(init: (@AnkoViewDslMarker _ViewPager).() -> Unit): androidx.viewpager.widget.ViewPager {
    return ankoView(`$$Anko$Factories$SupportV4ViewGroup`.VIEW_PAGER, theme = 0) { init() }
}

inline fun Activity.viewPager(): androidx.viewpager.widget.ViewPager = viewPager() {}

inline fun Activity.viewPager(init: (@AnkoViewDslMarker _ViewPager).() -> Unit): androidx.viewpager.widget.ViewPager {
    return ankoView(`$$Anko$Factories$SupportV4ViewGroup`.VIEW_PAGER, theme = 0) { init() }
}

