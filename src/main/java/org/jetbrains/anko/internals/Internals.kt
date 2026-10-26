/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

package org.jetbrains.anko.internals

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.UI
import java.io.Serializable

object AnkoInternals {
    const val NO_GETTER: String = "Property does not have a getter"

    fun noGetter(): Nothing = throw AnkoException("Property does not have a getter")

    private class AnkoContextThemeWrapper(base: Context?, val theme: Int) : ContextThemeWrapper(base, theme)

    fun <T : View> addView(manager: ViewManager, view: T) = when (manager) {
        is ViewGroup -> manager.addView(view)
        is AnkoContext<*> -> manager.addView(view, null)
        else -> throw AnkoException("$manager is the wrong parent")
    }

    fun <T : View> addView(ctx: Context, view: T) {
        ctx.UI { AnkoInternals.addView(this, view) }
    }

    fun <T : View> addView(activity: Activity, view: T) {
        createAnkoContext(activity, { AnkoInternals.addView(this, view) }, true)
    }

    fun wrapContextIfNeeded(ctx: Context, theme: Int): Context {
        return if (theme != 0 && (ctx !is AnkoContextThemeWrapper || ctx.theme != theme)) {
            // If the context isn't a ContextThemeWrapper, or it is but does not have
            // the same theme as we need, wrap it in a new wrapper
            AnkoContextThemeWrapper(ctx, theme)
        } else {
            ctx
        }
    }

    fun getContext(manager: ViewManager): Context = when (manager) {
        is ViewGroup -> manager.context
        is AnkoContext<*> -> manager.ctx
        else -> throw AnkoException("$manager is the wrong parent")
    }

    inline fun <T> T.createAnkoContext(
            ctx: Context,
            init: AnkoContext<T>.() -> Unit,
            setContentView: Boolean = false
    ): AnkoContext<T> {
        val dsl = AnkoContextImpl(ctx, this, setContentView)
        dsl.init()
        return dsl
    }

    @JvmStatic
    fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
        val intent = Intent(ctx, clazz)
        if (params.isNotEmpty()) fillIntentArguments(intent, params)
        return intent
    }

    @JvmStatic
    fun internalStartActivity(
            ctx: Context,
            activity: Class<out Activity>,
            params: Array<out Pair<String, Any?>>
    ) {
        ctx.startActivity(createIntent(ctx, activity, params))
    }

    @JvmStatic
    private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
        params.forEach {
            val value = it.second
            when (value) {
                null -> intent.putExtra(it.first, null as Serializable?)
                is Int -> intent.putExtra(it.first, value)
                is Long -> intent.putExtra(it.first, value)
                is CharSequence -> intent.putExtra(it.first, value)
                is String -> intent.putExtra(it.first, value)
                is Float -> intent.putExtra(it.first, value)
                is Double -> intent.putExtra(it.first, value)
                is Char -> intent.putExtra(it.first, value)
                is Short -> intent.putExtra(it.first, value)
                is Boolean -> intent.putExtra(it.first, value)
                is Serializable -> intent.putExtra(it.first, value)
                is Bundle -> intent.putExtra(it.first, value)
                is Parcelable -> intent.putExtra(it.first, value)
                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                    else -> throw AnkoException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
                is IntArray -> intent.putExtra(it.first, value)
                is LongArray -> intent.putExtra(it.first, value)
                is FloatArray -> intent.putExtra(it.first, value)
                is DoubleArray -> intent.putExtra(it.first, value)
                is CharArray -> intent.putExtra(it.first, value)
                is ShortArray -> intent.putExtra(it.first, value)
                is BooleanArray -> intent.putExtra(it.first, value)
                else -> throw AnkoException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            return@forEach
        }
    }
}
