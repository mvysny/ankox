/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

import android.graphics.drawable.Drawable
import android.view.View
import org.jetbrains.anko.internals.AnkoInternals.NO_GETTER
import org.jetbrains.anko.internals.AnkoInternals.noGetter
import kotlin.DeprecationLevel.ERROR

var View.backgroundDrawable: Drawable?
    inline get() = background
    set(value) = setBackgroundDrawable(value)

var View.leftPadding: Int
    inline get() = paddingLeft
    set(value) = setPadding(value, paddingTop, paddingRight, paddingBottom)

var View.topPadding: Int
    inline get() = paddingTop
    set(value) = setPadding(paddingLeft, value, paddingRight, paddingBottom)

var View.rightPadding: Int
    inline get() = paddingRight
    set(value) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

var View.bottomPadding: Int
    inline get() = paddingBottom
    set(value) = setPadding(paddingLeft, paddingTop, paddingRight, value)

var View.horizontalPadding: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    set(value) = setPadding(value, paddingTop, value, paddingBottom)

var View.verticalPadding: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    set(value) = setPadding(paddingLeft, value, paddingRight, value)

var View.padding: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = noGetter()
    inline set(value) = setPadding(value, value, value, value)
