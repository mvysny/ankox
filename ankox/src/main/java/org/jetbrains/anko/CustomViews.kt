/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.jetbrains.anko

import android.app.Activity
import android.content.Context
import android.view.ViewManager
import android.widget.LinearLayout
import org.jetbrains.anko.custom.ankoView

@PublishedApi
internal object `$$Anko$Factories$CustomViews` {
    val VERTICAL_LAYOUT_FACTORY = { ctx: Context ->
        val view = _LinearLayout(ctx)
        view.orientation = LinearLayout.VERTICAL
        view
    }
}

inline fun ViewManager.verticalLayout(theme: Int = 0): LinearLayout = verticalLayout(theme) {}
inline fun ViewManager.verticalLayout(theme: Int = 0, init: (@AnkoViewDslMarker _LinearLayout).() -> Unit): LinearLayout {
    return ankoView(`$$Anko$Factories$CustomViews`.VERTICAL_LAYOUT_FACTORY, theme, init)
}

inline fun Context.verticalLayout(theme: Int = 0): LinearLayout = verticalLayout(theme) {}
inline fun Context.verticalLayout(theme: Int = 0, init: (@AnkoViewDslMarker _LinearLayout).() -> Unit): LinearLayout {
    return ankoView(`$$Anko$Factories$CustomViews`.VERTICAL_LAYOUT_FACTORY, theme, init)
}

inline fun Activity.verticalLayout(theme: Int = 0): LinearLayout = verticalLayout(theme) {}
inline fun Activity.verticalLayout(theme: Int = 0, init: (@AnkoViewDslMarker _LinearLayout).() -> Unit): LinearLayout {
    return ankoView(`$$Anko$Factories$CustomViews`.VERTICAL_LAYOUT_FACTORY, theme, init)
}
