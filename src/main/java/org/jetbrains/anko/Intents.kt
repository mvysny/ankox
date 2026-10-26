/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.jetbrains.anko

import android.app.Activity
import android.app.Fragment
import android.content.Context
import org.jetbrains.anko.internals.AnkoInternals

inline fun <reified T: Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
        AnkoInternals.internalStartActivity(this, T::class.java, params)

inline fun <reified T: Activity> AnkoContext<*>.startActivity(vararg params: Pair<String, Any?>) =
        AnkoInternals.internalStartActivity(ctx, T::class.java, params)

@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun <reified T: Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) =
        AnkoInternals.internalStartActivity(activity, T::class.java, params)
