/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("Sdk15PropertiesKt")
package org.jetbrains.anko

import org.jetbrains.anko.*

import org.jetbrains.anko.internals.AnkoInternals
import kotlin.DeprecationLevel

var android.view.View.backgroundColor: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setBackgroundColor(v)

var android.view.View.backgroundResource: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setBackgroundResource(v)

var android.widget.TextView.textColor: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setTextColor(v)

var android.widget.TextView.lines: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setLines(v)

var android.widget.TextView.singleLine: Boolean
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setSingleLine(v)

var android.widget.ImageView.imageResource: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setImageResource(v)

var android.widget.TextView.textResource: Int
    @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
    set(v) = setText(v)

