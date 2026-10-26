/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext

@DslMarker
private annotation class AnkoContextDslMarker

@AnkoContextDslMarker
interface AnkoContext<out T> : ViewManager {
    val ctx: Context
    val owner: T
    val view: View

    override fun updateViewLayout(view: View, params: ViewGroup.LayoutParams) {
        throw UnsupportedOperationException()
    }

    override fun removeView(view: View) {
        throw UnsupportedOperationException()
    }

    companion object {
        fun create(ctx: Context, setContentView: Boolean = false): AnkoContext<Context>
                = AnkoContextImpl(ctx, ctx, setContentView)

        fun <T> create(ctx: Context, owner: T, setContentView: Boolean = false): AnkoContext<T>
                = AnkoContextImpl(ctx, owner, setContentView)
    }
}

open class AnkoContextImpl<T>(
        override val ctx: Context,
        override val owner: T,
        private val setContentView: Boolean
) : AnkoContext<T> {
    private var myView: View? = null

    override val view: View
        get() = myView ?: throw IllegalStateException("View was not set previously")

    override fun addView(view: View?, params: ViewGroup.LayoutParams?) {
        if (view == null) return

        if (myView != null) {
            alreadyHasView()
        }

        this.myView = view

        if (setContentView) {
            doAddView(ctx, view)
        }
    }

    private fun doAddView(context: Context, view: View) {
        when (context) {
            is Activity -> context.setContentView(view)
            is ContextWrapper -> doAddView(context.baseContext, view)
            else -> throw IllegalStateException("Context is not an Activity, can't set content view")
        }
    }

    open protected fun alreadyHasView(): Unit = throw IllegalStateException("View is already set: $myView")
}

inline fun Context.UI(setContentView: Boolean, init: AnkoContext<Context>.() -> Unit): AnkoContext<Context> =
        createAnkoContext(this, init, setContentView)

inline fun Context.UI(init: AnkoContext<Context>.() -> Unit): AnkoContext<Context> =
        createAnkoContext(this, init)

interface AnkoComponent<in T> {
    fun createView(ui: AnkoContext<T>): View
}

fun <T : Activity> AnkoComponent<T>.setContentView(activity: T): View =
        createView(AnkoContextImpl(activity, activity, true))
