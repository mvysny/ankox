/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko.custom

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.internals.AnkoInternals


inline fun <T : View> ViewManager.ankoView(factory: (ctx: Context) -> T, theme: Int, init: T.() -> Unit): T {
    val ctx = AnkoInternals.wrapContextIfNeeded(AnkoInternals.getContext(this), theme)
    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

inline fun <T : View> Context.ankoView(factory: (ctx: Context) -> T, theme: Int, init: T.() -> Unit): T {
    val ctx = AnkoInternals.wrapContextIfNeeded(this, theme)
    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

inline fun <T : View> Activity.ankoView(factory: (ctx: Context) -> T, theme: Int, init: T.() -> Unit): T {
    val ctx = AnkoInternals.wrapContextIfNeeded(this, theme)
    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}
