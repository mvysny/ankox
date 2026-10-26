/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.jetbrains.anko

import android.view.ViewGroup
import org.jetbrains.anko.internals.AnkoInternals.NO_GETTER
import org.jetbrains.anko.internals.AnkoInternals.noGetter
import kotlin.DeprecationLevel.ERROR

val matchParent: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT
val wrapContent: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT

var ViewGroup.MarginLayoutParams.verticalMargin: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    set(v) {
        topMargin = v
        bottomMargin = v
    }

var ViewGroup.MarginLayoutParams.horizontalMargin: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    set(v) { leftMargin = v; rightMargin = v }

var ViewGroup.MarginLayoutParams.margin: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    set(v) {
        leftMargin = v
        rightMargin = v
        topMargin = v
        bottomMargin = v
    }
