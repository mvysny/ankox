/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused", "NOTHING_TO_INLINE")
package org.jetbrains.anko

import android.content.Context
import android.content.res.Configuration

@Deprecated(message = "Inline", replaceWith = ReplaceWith("this"))
inline val Context.ctx: Context
    get() = this

inline val Context.displayMetrics: android.util.DisplayMetrics
    get() = resources.displayMetrics

inline val Context.configuration: Configuration
    get() = resources.configuration

inline val AnkoContext<*>.displayMetrics: android.util.DisplayMetrics
    get() = ctx.resources.displayMetrics

inline val AnkoContext<*>.configuration: Configuration
    get() = ctx.resources.configuration

inline val Configuration.landscape: Boolean
    get() = orientation == Configuration.ORIENTATION_LANDSCAPE
