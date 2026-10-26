/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.jetbrains.anko

import android.app.AlertDialog
import android.app.Fragment
import android.content.Context
import android.content.DialogInterface

inline fun AnkoContext<*>.alert(
        message: CharSequence,
        title: CharSequence? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = ctx.alert(message, title, init)

@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.alert(
        message: CharSequence,
        title: CharSequence? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = activity.alert(message, title, init)

fun Context.alert(
        message: CharSequence,
        title: CharSequence? = null,
        init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return AndroidAlertBuilder(this).apply {
        if (title != null) {
            this.title = title
        }
        this.message = message
        if (init != null) init()
    }
}

inline fun AnkoContext<*>.alert(
        message: Int,
        title: Int? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = ctx.alert(message, title, init)

@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.alert(
        message: Int,
        title: Int? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = activity.alert(message, title, init)

fun Context.alert(
        messageResource: Int,
        titleResource: Int? = null,
        init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<DialogInterface> {
    return AndroidAlertBuilder(this).apply {
        if (titleResource != null) {
            this.titleResource = titleResource
        }
        this.messageResource = messageResource
        if (init != null) init()
    }
}

inline fun AnkoContext<*>.alert(noinline init: AlertBuilder<DialogInterface>.() -> Unit) = ctx.alert(init)
inline fun Fragment.alert(noinline init: AlertBuilder<DialogInterface>.() -> Unit) = activity.alert(init)

fun Context.alert(init: AlertBuilder<DialogInterface>.() -> Unit): AlertBuilder<DialogInterface> =
        AndroidAlertBuilder(this).apply { init() }
