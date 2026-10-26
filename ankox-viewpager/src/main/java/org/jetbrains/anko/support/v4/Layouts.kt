/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("SupportV4LayoutsKt")
package org.jetbrains.anko.support.v4

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

open class _ViewPager(ctx: Context): ViewPager(ctx) {

    inline fun <T: View> T.lparams(
            init: ViewPager.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ViewPager.LayoutParams()
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            
    ): T {
        val layoutParams = ViewPager.LayoutParams()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            context: Context?,
            attrs: AttributeSet?,
            init: ViewPager.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ViewPager.LayoutParams(context!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            context: Context?,
            attrs: AttributeSet?
    ): T {
        val layoutParams = ViewPager.LayoutParams(context!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

}

