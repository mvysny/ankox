/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */


@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.jetbrains.anko

import android.app.Fragment
import android.content.Context
import android.widget.Toast

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
inline fun AnkoContext<*>.toast(message: Int) = ctx.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.toast(message: Int) = activity.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
inline fun Context.toast(message: Int): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
inline fun AnkoContext<*>.toast(message: CharSequence) = ctx.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.toast(message: CharSequence) = activity.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
inline fun Context.toast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
inline fun AnkoContext<*>.longToast(message: Int) = ctx.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.longToast(message: Int) = activity.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
inline fun Context.longToast(message: Int): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
inline fun AnkoContext<*>.longToast(message: CharSequence) = ctx.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
@Deprecated(message = "Use support library fragments instead. Framework fragments were deprecated in API 28.")
inline fun Fragment.longToast(message: CharSequence) = activity.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
inline fun Context.longToast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply {
            show()
        }
