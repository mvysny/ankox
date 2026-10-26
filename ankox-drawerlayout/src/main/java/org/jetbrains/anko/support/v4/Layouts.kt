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
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout

open class _DrawerLayout(ctx: Context): DrawerLayout(ctx) {

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height, gravity)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height, gravity)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: DrawerLayout.LayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: DrawerLayout.LayoutParams?
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

}

