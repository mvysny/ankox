/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused", "NOTHING_TO_INLINE")
package org.jetbrains.anko

import android.text.SpannableStringBuilder
import android.text.Spanned

inline fun buildSpanned(f: SpannableStringBuilder.() -> Unit): Spanned =
        SpannableStringBuilder().apply(f)

fun SpannableStringBuilder.append(text: CharSequence, vararg spans: Any) {
    val textLength = text.length
    append(text)
    spans.forEach { span ->
        setSpan(span, this.length - textLength, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}
